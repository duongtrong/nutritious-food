package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.config.Seeding;
import com.spring.dev2chuc.nutritious_food.model.Role;
import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.LoginRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/db")
public class DbController {

    private static final Logger LOGGER = Logger.getLogger(Seeding.class.getSimpleName());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    ComboRepository comboRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{name}")
    public ResponseEntity<?> authenticateUser(@PathVariable("name") String name) {
        try {

            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            String fileName = "src/main/java/com/spring/dev2chuc/nutritious_food/config/Seeding.java";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String getStartOfFile = getStartOfFile();
            String getFunctionSeedingUser = getFunctionSeedingUser();
            String getEndOfFile = getEndOfFile();
            printWriter.print( getStartOfFile + getFunctionSeedingUser + getEndOfFile);
            printWriter.close();
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                new ApiResponseError(HttpStatus.OK.value(),
                        "Write success"),
                HttpStatus.OK);
    }

    private String getStartOfFile() {
        return "package com.spring.dev2chuc.nutritious_food.config;\n" +
                "\n" +
                "import com.spring.dev2chuc.nutritious_food.model.*;\n" +
                "import com.spring.dev2chuc.nutritious_food.repository.*;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.boot.context.event.ApplicationReadyEvent;\n" +
                "import org.springframework.context.ApplicationListener;\n" +
                "import org.springframework.security.crypto.password.PasswordEncoder;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Collections;\n" +
                "import java.util.List;\n" +
                "import java.util.logging.Level;\n" +
                "import java.util.logging.Logger;\n" +
                "\n" +
                "@Component\n" +
                "public class Seeding implements ApplicationListener<ApplicationReadyEvent> {\n" +
                "\n" +
                "    private static final Logger LOGGER = Logger.getLogger(Seeding.class.getSimpleName());\n" +
                "\n" +
                "    @Autowired\n" +
                "    PasswordEncoder passwordEncoder;\n" +
                "\n" +
                "    @Autowired\n" +
                "    RoleRepository roleRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    UserRepository userRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    AddressRepository addressRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    UserProfileRepository userProfileRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    CategoryRepository categoryRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    FoodRepository foodRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    ComboRepository comboRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    ScheduleRepository scheduleRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    OrderDetailRepository orderDetailRepository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    OrderRepository orderRepository;\n" +
                "\n" +
                "    private List<User> userList = new ArrayList<>();\n" +
                "    private List<Address> addresses = new ArrayList<>();\n" +
                "    private List<UserProfile> userProfiles = new ArrayList<>();\n" +
                "    private List<History> histories = new ArrayList<>();\n" +
                "    private List<Category> categories = new ArrayList<>();\n" +
                "    private List<Food> foods = new ArrayList<>();\n" +
                "    private List<Combo> combos = new ArrayList<>();\n" +
                "    private List<Schedule> schedules = new ArrayList<>();\n" +
                "    private List<OrderDetail> orderDetails = new ArrayList<>();\n" +
                "    private List<Order> orders = new ArrayList<>();\n" +
                "\n" +
                "    @Override\n" +
                "    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {\n" +
                "        LOGGER.log(Level.INFO, String.format(\"Start seeding...\"));\n" +
                "        seedingUser();\n" +
                "        LOGGER.log(Level.INFO, String.format(\"Seeding success!\"));\n" +
                "    }\n" +
                "\n";
    }

    private String getEndOfFile() {
        return "}\n ";
    }

    private String getFunctionSeedingUser() {
        String str = "    private void seedingUser() {\n" +
                "        userRepository.deleteAll();\n" +
                "        userRepository.resetIncrement();\n" +
                "\n" +
                "        User user;\n" +
                "        Role userRole;\n\n";
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
            String roleName;
            if (user.getRoles().contains(roleAdmin)) {
                roleName = "RoleName.ROLE_ADMIN";
            } else {
                roleName = "RoleName.ROLE_USER";
            }

            str +=  "        user  = new User();\n" +
                    "        user.setName(\""+ user.getName() +"\");\n" +
                    "        user.setUsername(\""+ user.getUsername() +"\");\n" +
                    "        user.setEmail(\""+ user.getEmail() +"\");\n" +
                    "        user.setPhone(\""+ user.getPhone() +"\");\n" +
                    "        user.setPassword(\""+ user.getPassword() +"\");\n" +
                    "        user.setStatus("+ user.getStatus() +");\n" +
                    "        userRole = roleRepository.findByName("+ roleName +");\n" +
                    "        user.setRoles(Collections.singleton(userRole));\n" +
                    "        userList.add(user);\n\n";
        }
        str += "        userRepository.saveAll(userList);\n" +
                "    }\n";

        return str;
    }


    private String getFunctionSeedingUserProfile() {
        String str = "    private void seedingUser() {\n" +
                "        userRepository.deleteAll();\n" +
                "        userRepository.resetIncrement();\n" +
                "\n" +
                "        User user;\n" +
                "        Role userRole;\n\n";
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
            String roleName;
            if (user.getRoles().contains(roleAdmin)) {
                roleName = "RoleName.ROLE_ADMIN";
            } else {
                roleName = "RoleName.ROLE_USER";
            }

            str +=  "        user  = new User();\n" +
                    "        user.setName(\""+ user.getName() +"\");\n" +
                    "        user.setUsername(\""+ user.getUsername() +"\");\n" +
                    "        user.setEmail(\""+ user.getEmail() +"\");\n" +
                    "        user.setPhone(\""+ user.getPhone() +"\");\n" +
                    "        user.setPassword(\""+ user.getPassword() +"\");\n" +
                    "        user.setStatus("+ user.getStatus() +");\n" +
                    "        userRole = roleRepository.findByName("+ roleName +");\n" +
                    "        user.setRoles(Collections.singleton(userRole));\n" +
                    "        userRepository.save(user);\n\n";
        }
        str += "        userRepository.saveAll(userList);\n" +
                "    }\n";

        return str;
    }

    private void createFile(String fileName) {
        try {
            File file = new File("src/main/java/com/spring/dev2chuc/nutritious_food/config/Seeding.java");
            //Ở đây mình tạo file trong ổ D
            boolean isCreat = file.createNewFile();
            if (isCreat)
                System.out.print("Da tao file thanh cong!");
            else
                System.out.print("Tao file that bai");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
