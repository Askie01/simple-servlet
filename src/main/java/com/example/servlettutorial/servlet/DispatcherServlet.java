package com.example.servlettutorial.servlet;

import com.example.servlettutorial.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@WebServlet(name = "dispatcher-servlet", urlPatterns = "/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String httpMethod = req.getMethod();
            final String contextPath = req.getContextPath();
            final String requestURI = req.getRequestURI().replaceFirst(contextPath, "");
            final List<Class<?>> annotatedClasses = getAnnotatedClasses(RestController.class);
            final Class<? extends Annotation> lookupAnnotation = getLookupAnnotation(httpMethod);
            for (Class<?> controllerClass : annotatedClasses) {
                final Method[] declaredMethods = controllerClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.isAnnotationPresent(lookupAnnotation)) {
                        final Annotation annotation = method.getAnnotation(lookupAnnotation);
                        final Method pathMethod = annotation.annotationType().getMethod("path");
                        String pathValue = (String) pathMethod.invoke(annotation);
                        if (pathValue.equals(requestURI)) {
                            try {
                                final Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
                                method.invoke(controllerInstance, req, resp);
                            } catch (Exception e) {
                                log.error("Error invoking controller method: " + method.getName(), e);
                            }
                            return;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    private Class<? extends Annotation> getLookupAnnotation(String httpMethod) throws ServletException {
        switch (httpMethod) {
            case "GET":
                return GetMapping.class;
            case "POST":
                return PostMapping.class;
            case "DELETE":
                return DeleteMapping.class;
            case "PUT":
                return PutMapping.class;
            default:
                throw new ServletException("Unsupported HTTP method: " + httpMethod);
        }
    }

    private List<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotationClass) throws IOException, ClassNotFoundException {
        final List<Class<?>> classesInPackage = findClassesInPackage("com.example");
        // Filter classes annotated with @RestController
        List<Class<?>> annotatedClasses = new ArrayList<>();
        for (Class<?> clazz : classesInPackage) {
            if (clazz.isAnnotationPresent(annotationClass)) {
                annotatedClasses.add(clazz);
            }
        }
        return annotatedClasses;
    }

    private static List<Class<?>> findClassesInPackage(String packageName) throws IOException, ClassNotFoundException {
        // Convert package name to directory path
        String path = packageName.replace('.', '/');

        // Get resources in the path
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> directories = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            directories.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (File directory : directories) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        // Iterate over all files in the directory
        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively find classes in sub-packages
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                // Convert file name to class name and load the class
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6); // Remove ".class"
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }


}
