package com.jiang.advances.peace;


import com.jiang.advances.peace.entity.imp.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ServiceLoader;

@SpringBootApplication
public class CloudDemoApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(CloudDemoApplication.class, args);

//    }


    public static void main(String[] args) {
        System.out.println("##########");
        ServiceLoader<UserService> loader = ServiceLoader.load(UserService.class, Thread.currentThread().getContextClassLoader());
        loader.iterator();
        for(UserService service : loader) {
            System.out.println(service.getClass());
            System.out.println(service.getName()+"="+service.getSex());
        }
    }

//    public static void main(String[] args) {
//        System.out.println("##########");
//        ServiceLoader<Service> loader = ServiceLoader.load(Service.class, Thread.currentThread().getContextClassLoader());
//
//        for(Service service : loader) {
//            service.doService();
//            System.out.println(service.serviceType());
//        }
//    }

    /*public static void main(String[] args) {
        System.out.println("##########");
        ServiceLoader<ValidationProvider> loader = ServiceLoader.load(ValidationProvider.class);
        for(ValidationProvider service : loader) {
            System.out.println(service.getClass());
        }
    }*/

}
