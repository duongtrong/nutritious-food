package com.spring.dev2chuc.nutritious_food.config;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
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
    HistoryRepository historyRepository;

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

    private List<Role> roles = new ArrayList<>();
    private List<User> users = new ArrayList<>();
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
        deleteAll();
        seedingRole();
        seedingUser();
        seedingUserProfile();
        LOGGER.log(Level.INFO, String.format("Seeding success!"));
    }

    private void deleteAll() {
        historyRepository.deleteAll();
        userProfileRepository.deleteAll();
        addressRepository.deleteAll();
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        scheduleRepository.deleteAll();
        comboRepository.deleteAll();
        foodRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        historyRepository.resetIncrement();
        userProfileRepository.resetIncrement();
        addressRepository.resetIncrement();
        orderDetailRepository.resetIncrement();
        orderRepository.resetIncrement();
        scheduleRepository.resetIncrement();
        comboRepository.resetIncrement();
        foodRepository.resetIncrement();
        categoryRepository.resetIncrement();
        userRepository.resetIncrement();
        roleRepository.resetIncrement();    }

    private void seedingRole () {
        Role role;
        role = new Role(RoleName.ROLE_USER);
        roles.add(role);

        role = new Role(RoleName.ROLE_ADMIN);
        roles.add(role);

        roleRepository.saveAll(roles);
    }
    private void seedingUser() {
        User user;
        Role userRole;

        user  = new User();
        user.setName("admin001");
        user.setUsername("admin001");
        user.setEmail("admin@gmail.com");
        user.setPhone("09646548390");
        user.setPassword("$2a$10$ec18uzSdzaCDF0wi3dI0KOWYDoaDKY13Oq3MlkbHW4T.uQ61VcDr.");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("user0001");
        user.setUsername("user0001");
        user.setEmail("user0001@gmail.com");
        user.setPhone("user0001");
        user.setPassword("$2a$10$/x3FAKtcbeW1Qcd7793/fO5Mhi4.qs6UqNW3VqlTl67iZLmr0y6Rm");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("Nguyễn Danh Vượng");
        user.setUsername("danhvuong1");
        user.setEmail("danhvuong@gmail.com");
        user.setPhone("0964654839");
        user.setPassword("$2a$10$avr1IOz4x/JHFcY1jtFFcODTxVShctRezaXV9TYzZfXt7uN8Xr.WS");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("NguyenDanhVuong");
        user.setUsername("admin2");
        user.setEmail("admin2g@gmail.com");
        user.setPhone("0929033184");
        user.setPassword("$2a$10$cVcngnQZX/rmKggoFxn18.RvAb3utnXN3MXOZ1XJBnLL74eX.kXkK");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("NDVHades");
        user.setUsername("admindemo");
        user.setEmail("admindemog@gmail.com");
        user.setPhone("065483965");
        user.setPassword("$2a$10$axPfbuwugn/Awx/xs7EzF.H/yG1H5LxJIXfkrnY5N0sdHfeSN8m76");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("quyen1412");
        user.setUsername("quyen1412");
        user.setEmail("quyen1412@gmail.com");
        user.setPhone("0388044009");
        user.setPassword("$2a$10$M3S9XsFcc/VHwQ6vB/f/pu9LzMrRiRTOOp8GSIDvMeNhwuje8n3Sm");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        userRepository.saveAll(users);
    }
    private void seedingUserProfile () {
        UserProfile userProfile;
        Optional<User> user;
        List<Category> categoryList;
        List<Long> categoryIds = new ArrayList<>();
        

        user = userRepository.findById((long) 6);
        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        Set<Category> categorySet = new HashSet<>(categoryList);
        userProfile = new UserProfile();
        userProfile.setHeight(180);
        userProfile.setWeight(70);
        userProfile.setYearOfBirth(1999);
        userProfile.setGender(2);
        userProfile.setExerciseIntensity(1.4);
        userProfile.setBodyFat(null);
        userProfile.setBmrIndex(null);
        userProfile.setLbmIndex(null);
        userProfile.setTdeeIndex(null);
        userProfile.setStatus(1);
        userProfile.setCaloriesConsumed(1225);
        userProfile.setUser(user.get());
        userProfile.setCategories(categorySet);
        userProfiles.add(userProfile);

        userProfileRepository.saveAll(userProfiles);
    }
}
 