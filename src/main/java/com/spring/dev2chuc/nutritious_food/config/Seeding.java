package com.spring.dev2chuc.nutritious_food.config;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Seeding implements ApplicationListener<ApplicationReadyEvent> {

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

    private List<User> userList = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<UserProfile> userProfiles = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Combo> combos = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.log(Level.INFO, String.format("Start seeding..."));
        userRepository.disableForeignKeyCheck();
        seedingUser();
        userRepository.enableForeignKeyCheck();
        LOGGER.log(Level.INFO, String.format("Seeding success!"));
    }

    private void seedingUser() {
        userRepository.deleteAll();
        userRepository.resetIncrement();

        User user;
        Role userRole;

        user  = new User();
        user.setName("admin001");
        user.setUsername("admin001");
        user.setEmail("admin001@gmail.com");
        user.setPhone("09646548390");
        user.setPassword("$2a$10$ec18uzSdzaCDF0wi3dI0KOWYDoaDKY13Oq3MlkbHW4T.uQ61VcDr.");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("user001");
        user.setUsername("user001");
        user.setEmail("user001@gmail.com");
        user.setPhone("00002020");
        user.setPassword("$2a$10$/x3FAKtcbeW1Qcd7793/fO5Mhi4.qs6UqNW3VqlTl67iZLmr0y6Rm");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("Danh Vượng");
        user.setUsername("danhvuong");
        user.setEmail("danhvuong@gmail.com");
        user.setPhone("0964654839");
        user.setPassword("$2a$10$avr1IOz4x/JHFcY1jtFFcODTxVShctRezaXV9TYzZfXt7uN8Xr.WS");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("NguyenDanhVuong");
        user.setUsername("admin2");
        user.setEmail("admin2g@gmail.com");
        user.setPhone("0929033184");
        user.setPassword("$2a$10$cVcngnQZX/rmKggoFxn18.RvAb3utnXN3MXOZ1XJBnLL74eX.kXkK");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("NDVHades");
        user.setUsername("admindemo");
        user.setEmail("admindemog@gmail.com");
        user.setPhone("065483965");
        user.setPassword("$2a$10$axPfbuwugn/Awx/xs7EzF.H/yG1H5LxJIXfkrnY5N0sdHfeSN8m76");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("quyen1112");
        user.setUsername("quyen1112");
        user.setEmail("quyen1112@gmail.com");
        user.setPhone("0388044009");
        user.setPassword("$2a$10$CMdrksmbttGDZdpoaAQ5WeUmhhvvtWRR2oXyUqmRG7lU.flH0TaOe");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);

        user  = new User();
        user.setName("quyen1996");
        user.setUsername("quyen1996");
        user.setEmail("quyen1996@gmail.com");
        user.setPhone("0288044009");
        user.setPassword("$2a$10$3Sfim4heMCt9d8cdETaeROJgpwh/OQ/foQ5bEr9Wdhfa1dIzRhTC6");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        userList.add(user);


        userRepository.saveAll(userList);
    }
}
