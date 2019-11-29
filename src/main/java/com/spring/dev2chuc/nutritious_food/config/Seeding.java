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
        seedingAddress();
        seedingCategory();
        seedingFood();
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
        user.setName("adminn");
        user.setUsername("adminn");
        user.setEmail("admin@gmail.com");
        user.setPhone("09646548390");
        user.setPassword("$2a$10$ec18uzSdzaCDF0wi3dI0KOWYDoaDKY13Oq3MlkbHW4T.uQ61VcDr.");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        user  = new User();
        user.setName("s11111");
        user.setUsername("s11111");
        user.setEmail("s@gmail.com");
        user.setPhone("s111");
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
        user.setName("Duong Van Trong");
        user.setUsername("daintiness");
        user.setEmail("trongduong@gmail.com");
        user.setPhone("0981522133");
        user.setPassword("$2a$10$TfbSy2FiY8glS.sJE8lN9eqHSlGpoKI4qek1jNjXsMGc1s4ap7KsO");
        user.setStatus(1);
        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));
        users.add(user);

        userRepository.saveAll(users);
    }

    private void seedingUserProfile () {
        UserProfile userProfile;
        Optional<User> user;
        List<Category> categoryList;
        List<Long> categoryIds = new ArrayList<>();
        Set<Category> categorySet;
        

        userProfileRepository.saveAll(userProfiles);
    }
    private void seedingAddress () {
        Address address;
        Optional<User> user;

        addressRepository.saveAll(addresses);
    }

    private void seedingCategory () {
        Category category;

        category = new Category();
        category.setName("Nhóm đường bột");
        category.setParentId((long) 8);
        category.setDescription("Đây là nguồn cung cấp năng lượng chính cho cơ thể hoạt động. Ngoài ra, cũng nên ăn thay đổi các loại ngũ cốc khác (như khoai lang, khoai tây, ngô …) để làm đa dạng các loại thực phẩm, tăng cường lợi ích cho sức khỏe Với người trưởng thành, năng lượng từ nhóm các chất bột đường chỉ nên chiếm 60-65% tổng năng lượng khẩu phần, phần còn lại do chất béo cung cấp (chiếm 20-25%) và chất đạm (chiếm 10-15%). ");
        category.setImage("http://images.alobacsi.vn/Images/Uploaded/Share/2012/09/21/62eDam-va-bot-duong-cung-cap-cho-te-bao-nao-phat-trien.jpg");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Nhóm chất đạm");
        category.setParentId((long) 8);
        category.setDescription("Cung cấp các thành phần thiết yếu để xây dựng nên cơ thể, đảm bảo cơ thể tăng trưởng và duy trì nhiều hoạt động sống, tăng cường sức đề kháng của cơ thể chống lại bệnh tật. Cần ăn phối hợp cả thực phẩm giàu đạm động vật (như thịt, cá, trứng, sữa...) và đạm thực vật (từ các loại đậu, đỗ…). Các loại thịt đỏ (như thịt lợn, thịt bò …) có nhiều sắt giúp phòng chống thiếu máu thiếu sắt, đặc biệt quan trọng đối với trẻ nhỏ, phụ nữ có thai và phụ nữ tuổi sinh đẻ. Tuy nhiên ăn nhiều thịt đỏ lại làm tăng nguy cơ mắc các bệnh tim mạch, ung thư, gout… do đó không nên ăn quá nhiều. Nên tăng cường ăn các loại thịt gia cầm (như gà, vịt, ngan, chim…) và nên ăn ít nhất 3 bữa cá mỗi tuần. Các loại cá nhỏ nấu nhừ ăn cả xương, tôm và tép ăn cả vỏ và cua là nguồn cung cấp canxi tốt cho cơ thể. Các loại hạt đậu, đỗ cũng là nguồn đạm thực vật tốt.");
        category.setImage("https://yeutre.vn/cdn/medias/uploads/135/135425-chat-dam-co-nhieu-trong-cac-thuc-pham-tu-thit-va-trung-660x440.jpg");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Nhóm chất béo");
        category.setParentId((long) 8);
        category.setDescription("Cung cấp năng lượng cho cơ thể hoạt động và tăng trưởng, hỗ trợ hấp thu các vitamin tan trong dầu, mỡ như vitamin A, D, E, K. Mỡ cá và mỡ gia cầm lại có nhiều chất béo chưa bão hòa, đặc biệt là omega 3, omega 6, omega 9, rất có lợi cho sức khỏe. Các loại dầu thực vật cũng thường có nhiều chất béo chưa bão hòa nên có tác dụng tốt cho tim mạch và được khuyến khích tiêu thụ như dầu đậu nành, dầu mè, dầu hướng dương, dầu hạt cải Không nên ăn quá nhiều các món xào, rán, nướng, mà nên tăng cường ăn các món luộc, hấp để giảm mất mát các chất dinh dưỡng và không làm biến đổi thực phẩm thành các chất có thể gây tác hại cho sức khỏe");
        category.setImage("http://media.vienyhocungdung.vn/Upload/21/2019/Thang_6/5ee4f277-ad40-4a4a-b622-03e8162a70bf.jpg");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Nhóm vitamin và khoáng chất ");
        category.setParentId((long) 8);
        category.setDescription("Cung cấp các yếu tố vi lượng cũng như các chất bảo vệ, giúp cơ thể phát triển khỏe mạnh, tăng cường sức đề kháng, chống lại bệnh tật ở mọi lứa tuổi. Các loại rau lá màu xanh sẫm và các loại rau và quả màu vàng, đỏ là nguồn cung cấp vitamin A giúp sáng mắt, tăng sức đề kháng, cung cấp chất sắt giúp chống thiếu máu thiếu sắt, đặc biệt giúp cho cơ thể trẻ em tăng trưởng và phát triển tốt. Ăn ít rau và trái cây được cho là nguyên nhân của 1,7 triệu trường hợp tử vong, chiếm 2,8% tổng số trường hợp tử vong trên thế giới. Ăn ít rau và trái cây còn được ước tính là nguyên nhân của 19% số ung thư dạ dày ruột, 31% các bệnh thiếu máu tim cục bộ, và 11% số trường hợp đột quỵ");
        category.setImage("http://nhatkybe.vn/sites/default/files/imagecache/450x300/images/hieu_toan_dien_ve_thuc_an_chua_vitamin_va_khoang_chat.jpg");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Các loại bệnh");
        category.setParentId((long) 1);
        category.setDescription("Bệnh là quá trình hoạt động không bình thường của cơ thể sinh vật từ nguyên nhân khởi thuỷ đến hậu quả cuối cùng. Bệnh có thể gặp ở người, động vật hay thực vật. Có rất nhiều nguyên nhân sinh ra bệnh, nhưng có thể chia thành ba loại chính: Bệnh do bản thân cơ thể sinh vật có khuyết tật như di truyền bẩm sinh hay rối loạn sinh lý. Bệnh do hoàn cảnh sống của sinh vật khắc nghiệt như quá lạnh, quá nóng, bị ngộ độc, không đủ chất dinh dưỡng. Bệnh do bị các sinh vật khác (nhất là các vi sinh vật) ký sinh.");
        category.setImage("1");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Bệnh tiểu đường");
        category.setParentId((long) 5);
        category.setDescription("Người bệnh tiểu đường nên ăn rau nhiều hơn trong thực đơn của mình thông qua các cách chế biến đơn giản như ăn sống, hấp, luộc, rau trộn nhưng không nên sử dụng nhiều loại sốt có chất béo.");
        category.setImage("1");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Bà bầu");
        category.setParentId((long) 5);
        category.setDescription("Người bệnh tiểu đường nên ăn rau nhiều hơn trong thực đơn của mình thông qua các cách chế biến đơn giản như ăn sống, hấp, luộc, rau trộn nhưng không nên sử dụng nhiều loại sốt có chất béo.");
        category.setImage("1");
        category.setStatus(1);
        categories.add(category);

        category = new Category();
        category.setName("Dinh Dưỡng");
        category.setParentId((long) 1);
        category.setDescription("Dinh dưỡng là việc cung cấp các chất cần thiết cho các tế bào và các sinh vật để hỗ trợ sự sống. Nó bao gồm các hoạt động ăn uống; hấp thu, vận chuyển và sử dụng các chất dinh dưỡng; bài tiết các chất thải. Chế độ ăn uống của một sinh vật, phụ thuộc phần lớn vào độ ngon của thức ăn.");
        category.setImage("https://media1.nguoiduatin.vn/media/nhap-bai-qc/2019/01/15/hoatri1.jpg");
        category.setStatus(1);
        categories.add(category);

        categoryRepository.saveAll(categories);
    }

    private void seedingFood () {
        Food food;
        List<Category> categoryList;
        List<Long> categoryIds = new ArrayList<>();
        Set<Category> categorySet;

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("1 Chén Cơm Trắng");
        food.setImage("https://cdn02.static-adayroi.com/0/2019/04/19/1555648595745_3340302.jpg");
        food.setPrice((float)500.0);
        food.setCarbonhydrates((float)28.2);
        food.setProtein((float)2.7);
        food.setLipid((float)0.3);
        food.setXenluloza((float)0.23);
        food.setCanxi((float)10.0);
        food.setIron((float)0.1);
        food.setZinc((float)0.4);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)130.0);
        food.setWeight((float)134.0);
        food.setStatus(1);
        food.setPrice((float)500.0);
        foods.add(food);

        categoryIds.add((long) 3);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bầu xào trứng");
        food.setImage("https://xaongon.net/wp-content/uploads/2016/11/cach-lam-bau-xao-trung-don-gian-day-nang-luong-cho-tuan-moi-min.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)4.0);
        food.setProtein((float)4.0);
        food.setLipid((float)8.5);
        food.setXenluloza((float)1.3);
        food.setCanxi((float)30.6);
        food.setIron((float)0.72);
        food.setZinc((float)0.4);
        food.setVitaminA((float)93.6);
        food.setVitaminB((float)60.6);
        food.setVitaminC((float)240.0);
        food.setVitaminD((float)1.3);
        food.setVitaminE((float)875.0);
        food.setCalorie((float)109.0);
        food.setWeight((float)75.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bò cuốn lá lốt");
        food.setImage("https://cdn.huongnghiepaau.com/wp-content/uploads/2019/01/bo-cuon-la-lot.jpg");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)66.55);
        food.setProtein((float)24.5);
        food.setLipid((float)6.25);
        food.setXenluloza((float)3.43);
        food.setCanxi((float)45.0);
        food.setIron((float)6.5);
        food.setZinc((float)0.004);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)2925.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)875.0);
        food.setCalorie((float)421.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá bạc má chiên");
        food.setImage("http://streaming1.danviet.vn/upload/1-2018/images/2018-01-19/Ca-bac-ma-chiem-mam-toi-va-canh-chua-ech-doi-vi-ngay-gio-lanh-1--2--1516330777-width660height440.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)13.1);
        food.setLipid((float)9.1);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)0.05);
        food.setIron((float)0.002);
        food.setZinc((float)0.0);
        food.setVitaminA((float)24.0);
        food.setVitaminB((float)90.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)135.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá bạc má kho");
        food.setImage("https://thucthan.com/media/2019/08/ca-bac-ma-kho-ca-chua/ca-bac-ma-kho-ca-chua.jpg");
        food.setPrice((float)9000.0);
        food.setCarbonhydrates((float)8.7);
        food.setProtein((float)21.1);
        food.setLipid((float)5.3);
        food.setXenluloza((float)0.04);
        food.setCanxi((float)0.05);
        food.setIron((float)0.002);
        food.setZinc((float)0.001);
        food.setVitaminA((float)30.0);
        food.setVitaminB((float)336.0);
        food.setVitaminC((float)13000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)540.0);
        food.setCalorie((float)167.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)9000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá cơm lăn bột chiên");
        food.setImage("https://anh.eva.vn//upload/4-2014/images/2014-10-20/1413799868-ca-com-chien-bot-4.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)17.3);
        food.setProtein((float)9.7);
        food.setLipid((float)9.7);
        food.setXenluloza((float)0.15);
        food.setCanxi((float)0.464);
        food.setIron((float)0.009);
        food.setZinc((float)0.006);
        food.setVitaminA((float)24.0);
        food.setVitaminB((float)201.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)1.725);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)195.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá chép chưng tương");
        food.setImage("https://pastaxi-manager.onepas.vn/content/uploads/articles/cach-lam-ca-chep-chung-tuong/cach-lam-ca-chep-chung-tuong-1.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)7.9);
        food.setProtein((float)16.4);
        food.setLipid((float)6.6);
        food.setXenluloza((float)0.11);
        food.setCanxi((float)0.017);
        food.setIron((float)0.001);
        food.setZinc((float)0.002);
        food.setVitaminA((float)9.0);
        food.setVitaminB((float)2012.0);
        food.setVitaminC((float)1600.0);
        food.setVitaminD((float)124.7);
        food.setVitaminE((float)630.0);
        food.setCalorie((float)39.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá chim chiên sốt cà");
        food.setImage("http://cakho.net/wp-content/uploads/2015/01/ca-chim-sot1.jpg");
        food.setPrice((float)30000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)2.6);
        food.setLipid((float)1.9);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)0.02);
        food.setIron((float)0.001);
        food.setZinc((float)0.001);
        food.setVitaminA((float)27000.0);
        food.setVitaminB((float)90.1);
        food.setVitaminC((float)13500.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)28.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)30000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá đối chiên");
        food.setImage("http://tungthanh.com/pic/Product/290_636232930244728306_HasThumb_Thumb.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)8.8);
        food.setLipid((float)7.7);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)0.021);
        food.setIron((float)0.001);
        food.setZinc((float)0.001);
        food.setVitaminA((float)45.0);
        food.setVitaminB((float)1450.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)1000.0);
        food.setCalorie((float)108.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá đối kho");
        food.setImage("https://monngonmoingay.com/wp-content/uploads/2015/08/CaDoiKhoCaChua_CQC9514.png");
        food.setPrice((float)12000.0);
        food.setCarbonhydrates((float)4.4);
        food.setProtein((float)10.2);
        food.setLipid((float)2.7);
        food.setXenluloza((float)0.02);
        food.setCanxi((float)0.021);
        food.setIron((float)0.001);
        food.setZinc((float)0.001);
        food.setVitaminA((float)45.0);
        food.setVitaminB((float)1450.0);
        food.setVitaminC((float)13000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)1000.0);
        food.setCalorie((float)108.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)12000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá lóc chiên tỏi ớt");
        food.setImage("https://vcdn-ngoisao.vnecdn.net/2019/03/26/54730836-1016111951928421-7855-8852-2802-1553579360.png");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)14.9);
        food.setLipid((float)12.2);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)0.09);
        food.setIron((float)0.0);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)13000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)169.0);
        food.setWeight((float)0.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh bao nhân thịt");
        food.setImage("http://lambanh365.com/wp-content/uploads/2015/03/Cach_lam_banh_bao_nhan_thit_1.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)36.8);
        food.setProtein((float)11.44);
        food.setLipid((float)11.88);
        food.setXenluloza((float)0.924);
        food.setCanxi((float)0.08);
        food.setIron((float)0.001);
        food.setZinc((float)0.001);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)303.6);
        food.setWeight((float)110.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh bèo ");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/b%C3%A1nh+b%C3%A8o.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)17.52);
        food.setProtein((float)1.8);
        food.setLipid((float)4.2);
        food.setXenluloza((float)0.63);
        food.setCanxi((float)84.79);
        food.setIron((float)2.45);
        food.setZinc((float)0.52);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)117.6);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh bột lọc");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/b%C3%A1nh+b%E1%BB%99t+l%E1%BB%8Dc.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)6.11);
        food.setProtein((float)3.43);
        food.setLipid((float)1.53);
        food.setXenluloza((float)0.22);
        food.setCanxi((float)37.5);
        food.setIron((float)0.67);
        food.setZinc((float)0.16);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)48.0);
        food.setWeight((float)40.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh chay");
        food.setImage("https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2017/03/28/huong-dan-cach-lam-banh-chay-vin-tra-xanh-kieu-moi11490687368.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)8.5);
        food.setProtein((float)0.99);
        food.setLipid((float)0.93);
        food.setXenluloza((float)0.114);
        food.setCanxi((float)3.66);
        food.setIron((float)0.234);
        food.setZinc((float)0.096);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)46.78);
        food.setWeight((float)30.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh chín tầng mây");
        food.setImage("https://i.ytimg.com/vi/QbJ60Evcb3s/maxresdefault.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)12.21);
        food.setProtein((float)0.06);
        food.setLipid((float)0.18);
        food.setXenluloza((float)0.018);
        food.setCanxi((float)17.52);
        food.setIron((float)0.282);
        food.setZinc((float)0.267);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)50.78);
        food.setWeight((float)30.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh chưng");
        food.setImage("https://khotangvanmau.com/wp-content/uploads/2016/12/van-mau-gioi-thieu-ve-chiec-banh-trung-ngay-tet.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)31.01);
        food.setProtein((float)4.3);
        food.setLipid((float)4.2);
        food.setXenluloza((float)0.59);
        food.setCanxi((float)25.6);
        food.setIron((float)0.94);
        food.setZinc((float)1.4);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)181.4);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh cuốn");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/b%C3%A1nh+cu%E1%BB%91n.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)16.85);
        food.setProtein((float)3.0);
        food.setLipid((float)5.3);
        food.setXenluloza((float)0.55);
        food.setCanxi((float)73.44);
        food.setIron((float)0.5);
        food.setZinc((float)19.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)129.3);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh đúc nóng");
        food.setImage("https://cdn.huongnghiepaau.com/wp-content/uploads/2019/04/banh-duc-man-thom-ngon.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)4.42);
        food.setProtein((float)1.95);
        food.setLipid((float)3.15);
        food.setXenluloza((float)0.05);
        food.setCanxi((float)30.65);
        food.setIron((float)0.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)54.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh giò");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/b%C3%A1nh+gi%C3%B2.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)28.7);
        food.setProtein((float)5.13);
        food.setLipid((float)33.6);
        food.setXenluloza((float)0.05);
        food.setCanxi((float)63.65);
        food.setIron((float)0.703);
        food.setZinc((float)2.3);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)437.0);
        food.setWeight((float)190.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh khoai mì");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/b%C3%A1nh+khoai.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)27.15);
        food.setProtein((float)4.1);
        food.setLipid((float)1.6);
        food.setXenluloza((float)0.49);
        food.setCanxi((float)4.68);
        food.setIron((float)1.05);
        food.setZinc((float)0.57);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)141.36);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh khúc");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/B%C3%A1nh_kh%C3%BAc.JPG");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)73.65);
        food.setProtein((float)6.15);
        food.setLipid((float)5.7);
        food.setXenluloza((float)0.9);
        food.setCanxi((float)41.25);
        food.setIron((float)1.695);
        food.setZinc((float)2.355);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)374.0);
        food.setWeight((float)150.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bánh mì pate");
        food.setImage("http://product.hstatic.net/1000315949/product/banh_mi__pate_cha_bong_master.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)62.6);
        food.setProtein((float)19.6);
        food.setLipid((float)22.0);
        food.setXenluloza((float)1.2);
        food.setCanxi((float)80.6);
        food.setIron((float)6.9);
        food.setZinc((float)1.18);
        food.setVitaminA((float)0.9);
        food.setVitaminB((float)0.91);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)531.6);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bún bò Nam Bộ");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/B%C3%BAn+b%C3%B2+Nam+b%E1%BB%99.jpg");
        food.setPrice((float)30000.0);
        food.setCarbonhydrates((float)76.5);
        food.setProtein((float)16.0);
        food.setLipid((float)3.0);
        food.setXenluloza((float)12.0);
        food.setCanxi((float)436.5);
        food.setIron((float)2.75);
        food.setZinc((float)0.65);
        food.setVitaminA((float)1.0);
        food.setVitaminB((float)135.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)405.6);
        food.setWeight((float)500.0);
        food.setStatus(1);
        food.setPrice((float)30000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bún chả");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/B%C3%BAn+ch%E1%BA%A3.jpg");
        food.setPrice((float)25000.0);
        food.setCarbonhydrates((float)26.25);
        food.setProtein((float)9.15);
        food.setLipid((float)6.6);
        food.setXenluloza((float)0.825);
        food.setCanxi((float)136.65);
        food.setIron((float)1.485);
        food.setZinc((float)1.155);
        food.setVitaminA((float)2.0);
        food.setVitaminB((float)6050.0);
        food.setVitaminC((float)0.001);
        food.setVitaminD((float)0.5);
        food.setVitaminE((float)210.0);
        food.setCalorie((float)203.4);
        food.setWeight((float)150.0);
        food.setStatus(1);
        food.setPrice((float)25000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bún riêu cua");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/B%C3%BAn+cua.jpg");
        food.setPrice((float)35000.0);
        food.setCarbonhydrates((float)77.35);
        food.setProtein((float)17.85);
        food.setLipid((float)7.65);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)832.15);
        food.setIron((float)17.595);
        food.setZinc((float)3.4);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)150.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)450.0);
        food.setWeight((float)850.0);
        food.setStatus(1);
        food.setPrice((float)35000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bún ốc");
        food.setImage("http://vikinutri.com/Medias/images/Anh+thuc+pham/B%C3%BAn+%E1%BB%91c.jpg");
        food.setPrice((float)25000.0);
        food.setCarbonhydrates((float)58.0);
        food.setProtein((float)17.8);
        food.setLipid((float)12.2);
        food.setXenluloza((float)2.76);
        food.setCanxi((float)1177.15);
        food.setIron((float)17.595);
        food.setZinc((float)1.885);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)1.9);
        food.setCalorie((float)414.0);
        food.setWeight((float)650.0);
        food.setStatus(1);
        food.setPrice((float)25000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cá thát lát viên kho");
        food.setImage("http://img.tinbaihay.net/ThumbImages/01/cath/ca-thac-lac-kho-thom-ngon-bfd6_450.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)5.25);
        food.setProtein((float)23.5);
        food.setLipid((float)4.2);
        food.setXenluloza((float)0.015);
        food.setCanxi((float)75.0);
        food.setIron((float)1.95);
        food.setZinc((float)0.0);
        food.setVitaminA((float)500.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)150.0);
        food.setWeight((float)150.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh bắp cải");
        food.setImage("https://img-global.cpcdn.com/recipes/1bcd1006f87a28ee/751x532cq70/canh-b%E1%BA%AFp-c%E1%BA%A3i-th%E1%BB%8Bt-bam-recipe-main-photo.jpg");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)2.8);
        food.setProtein((float)1.8);
        food.setLipid((float)2.1);
        food.setXenluloza((float)0.82);
        food.setCanxi((float)62.4);
        food.setIron((float)1.43);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)30000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)37.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh bầu nấu tôm");
        food.setImage("https://ameovat.com/wp-content/uploads/2018/09/cach-lam-canh-bau-nau-tom06-600x491.jpg");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)1.5);
        food.setProtein((float)16.34);
        food.setLipid((float)2.1);
        food.setXenluloza((float)0.52);
        food.setCanxi((float)51.4);
        food.setIron((float)4.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)500.0);
        food.setVitaminC((float)1200.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)99.4);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh bí đao");
        food.setImage("https://2sao.vietnamnetjsc.vn/images/2018/05/25/09/31/giam-can-bang-bi-dao.jpg");
        food.setPrice((float)500.0);
        food.setCarbonhydrates((float)1.3);
        food.setProtein((float)1.2);
        food.setLipid((float)1.05);
        food.setXenluloza((float)0.52);
        food.setCanxi((float)51.4);
        food.setIron((float)4.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)500.0);
        food.setVitaminC((float)800.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)12.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)500.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh cải ngọt");
        food.setImage("https://wikiohana.net/wp-content/uploads/2018/11/canh-rau-cai-ngot-nau-thit-bam.jpg");
        food.setPrice((float)1500.0);
        food.setCarbonhydrates((float)1.1);
        food.setProtein((float)1.7);
        food.setLipid((float)2.1);
        food.setXenluloza((float)0.9);
        food.setCanxi((float)4.45);
        food.setIron((float)0.095);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)10.0);
        food.setVitaminC((float)1275.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)30.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)1500.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh rau dền");
        food.setImage("https://img-global.cpcdn.com/005_recipes/d3b82b6707307c34/590x315cq70/photo.jpg");
        food.setPrice((float)1500.0);
        food.setCarbonhydrates((float)0.1);
        food.setProtein((float)0.9);
        food.setLipid((float)2.1);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)36.8);
        food.setIron((float)0.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)190.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)8000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)22.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)1500.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Canh rau ngót");
        food.setImage("https://i0.wp.com/congthucmonngon.com/wp-content/uploads/2019/06/canh-rau-ngot-nau-tom.jpg?ssl=1");
        food.setPrice((float)1500.0);
        food.setCarbonhydrates((float)0.7);
        food.setProtein((float)1.9);
        food.setLipid((float)2.1);
        food.setXenluloza((float)0.45);
        food.setCanxi((float)16.9);
        food.setIron((float)0.27);
        food.setZinc((float)0.94);
        food.setVitaminA((float)665.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)18500.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)29.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)1500.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Chả giò chiên");
        food.setImage("https://i.ytimg.com/vi/FPHoCU_VYWA/hqdefault.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)1.8);
        food.setProtein((float)0.9);
        food.setLipid((float)1.05);
        food.setXenluloza((float)0.05);
        food.setCanxi((float)42.41);
        food.setIron((float)1.1);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)448.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)20.5);
        food.setWeight((float)150.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Sườn nướng");
        food.setImage("http://sanhangtot.com/media/news/393_suon_nuong_mat_ong.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)1.0);
        food.setProtein((float)10.3);
        food.setLipid((float)1.05);
        food.setXenluloza((float)0.01);
        food.setCanxi((float)10.4);
        food.setIron((float)0.6);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)1200.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)111.0);
        food.setWeight((float)150.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Trứng vịt lộn");
        food.setImage("https://lh3.googleusercontent.com/Z86eUZtZHk9NQ9wgFdUKstWl0jamtpjyuLyJzQXKpr14TkH8yXG5lcfyzJokzkeOQzQ4r5jdh-h_QXmnBWv0M9CZOB-IDA");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)4.0);
        food.setProtein((float)13.6);
        food.setLipid((float)12.4);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)81.0);
        food.setIron((float)3.0);
        food.setZinc((float)0.0);
        food.setVitaminA((float)875.0);
        food.setVitaminB((float)370.0);
        food.setVitaminC((float)3000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)182.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 3);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Sữa tươi tiệt trùng ");
        food.setImage("https://nhanvan.vn/images/detailed/45/_S%E1%BB%AFa_T%C6%B0%C6%A1i_Ti%E1%BB%87t_Tr%C3%B9ng_H%C6%B0%C6%A1ng_Socola_Vinamilk_100___110ml_.jpeg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)9.8);
        food.setProtein((float)5.4);
        food.setLipid((float)6.3);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)216.0);
        food.setIron((float)2.7);
        food.setZinc((float)2.16);
        food.setVitaminA((float)116.0);
        food.setVitaminB((float)1296.0);
        food.setVitaminC((float)11700.0);
        food.setVitaminD((float)2.01);
        food.setVitaminE((float)720.0);
        food.setCalorie((float)141.66);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Sữa đâu nành ");
        food.setImage("http://tieudungvne.mediacdn.vn/thumb_w/1200/2019/5/20/1114510-15583224785381977809742.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)12.0);
        food.setProtein((float)56.6);
        food.setLipid((float)3.6);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)50.0);
        food.setIron((float)1.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)1.8);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)108.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Sữa dê tươi");
        food.setImage("http://cuchifarmmilk.com/wp-content/uploads/2018/11/sua-de-lam-dep.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)9.0);
        food.setProtein((float)8.2);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)268.0);
        food.setIron((float)0.2);
        food.setZinc((float)0.6);
        food.setVitaminA((float)118.8);
        food.setVitaminB((float)400.0);
        food.setVitaminC((float)2600.0);
        food.setVitaminD((float)0.6);
        food.setVitaminE((float)200.0);
        food.setCalorie((float)108.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Sữa bò tươi");
        food.setImage("https://www.trungtamdinhduong.org/wp-content/uploads/2018/04/sua-tuoi-co-beo-600x340.jpg");
        food.setPrice((float)15000.0);
        food.setCarbonhydrates((float)8.64);
        food.setProtein((float)7.8);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)218.0);
        food.setIron((float)0.18);
        food.setZinc((float)0.72);
        food.setVitaminA((float)90.0);
        food.setVitaminB((float)638.0);
        food.setVitaminC((float)1800.0);
        food.setVitaminD((float)1.8);
        food.setVitaminE((float)108.0);
        food.setCalorie((float)133.2);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)15000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Gà xào sả ớt");
        food.setImage("https://image-us.eva.vn/upload/1-2019/images/2019-03-21/1553152666-796-thumbnail.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)4.7);
        food.setProtein((float)20.4);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)16.8);
        food.setIron((float)2.1);
        food.setZinc((float)0.0);
        food.setVitaminA((float)168.0);
        food.setVitaminB((float)560.0);
        food.setVitaminC((float)5600.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)272.0);
        food.setWeight((float)140.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Gan heo xào");
        food.setImage("https://img-global.cpcdn.com/recipes/1d56325a5e11d232/751x532cq70/gan-heo-xao-hanh-tay-recipe-main-photo.jpg");
        food.setPrice((float)12000.0);
        food.setCarbonhydrates((float)3.4);
        food.setProtein((float)24.8);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.15);
        food.setCanxi((float)11.9);
        food.setIron((float)20.4);
        food.setZinc((float)0.0);
        food.setVitaminA((float)10200.0);
        food.setVitaminB((float)2501.7);
        food.setVitaminC((float)30600.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)200.0);
        food.setWeight((float)170.0);
        food.setStatus(1);
        food.setPrice((float)12000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Gỏi tôm cuốn");
        food.setImage("https://www.cet.edu.vn/wp-content/uploads/2018/11/goi-cuon-tom-thit.jpg");
        food.setPrice((float)9000.0);
        food.setCarbonhydrates((float)17.8);
        food.setProtein((float)7.7);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.15);
        food.setCanxi((float)94.4);
        food.setIron((float)1.84);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)147.0);
        food.setWeight((float)90.0);
        food.setStatus(1);
        food.setPrice((float)9000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Lạp xưởng chiên");
        food.setImage("https://yurifood.com/wp-content/uploads/2018/05/Lap-xuong-chien.gif");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)0.9);
        food.setProtein((float)10.4);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)26.0);
        food.setIron((float)1.5);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)350.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)293.0);
        food.setWeight((float)50.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Mực xào xả ớt");
        food.setImage("http://nauancungturtle.com/wp-content/uploads/2018/12/24.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)0.1);
        food.setProtein((float)31.0);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.03);
        food.setCanxi((float)28.0);
        food.setIron((float)1.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)184.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Mực xào thập cẩm");
        food.setImage("https://img-global.cpcdn.com/recipes/2528955_afda248d34aa9fb1/751x532cq70/m%E1%BB%B1c-t%C6%B0%C6%A1i-xao-th%E1%BA%ADp-c%E1%BA%A9m-recipe-main-photo.jpg");
        food.setPrice((float)18000.0);
        food.setCarbonhydrates((float)3.5);
        food.setProtein((float)17.4);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.58);
        food.setCanxi((float)28.0);
        food.setIron((float)1.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)12000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)136.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)18000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Thịt heo quay");
        food.setImage("https://www.hoidaubepaau.com/wp-content/uploads/2015/12/thit-heo-quay-da-gion.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)9.2);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)5.4);
        food.setIron((float)0.9);
        food.setZinc((float)0.0);
        food.setVitaminA((float)6.0);
        food.setVitaminB((float)420.0);
        food.setVitaminC((float)1200.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)146.0);
        food.setWeight((float)60.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Thịt bò xào hành tây");
        food.setImage("https://agiadinh.net/wp-content/uploads/2018/11/cach-lam-thit-bo-xao-hanh-tay.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)5.8);
        food.setProtein((float)11.8);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.77);
        food.setCanxi((float)12.0);
        food.setIron((float)3.1);
        food.setZinc((float)4.55);
        food.setVitaminA((float)12.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)350.0);
        food.setCalorie((float)132.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Thịt bò xào măng");
        food.setImage("https://cdn.phunuvagiadinh.vn/auto/14_8_2018/mang-xao-thit-bo--2018-08-14-12-07.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)0.0);
        food.setProtein((float)10.5);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)12.0);
        food.setIron((float)3.1);
        food.setZinc((float)4.55);
        food.setVitaminA((float)12.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)1800.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)350.0);
        food.setCalorie((float)104.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Tai heo phá lấu");
        food.setImage("https://khamphamonngon.com/wp-content/uploads/2014/12/cach-lam-pha-lau-lo-tai-heo.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)1.6);
        food.setProtein((float)42.0);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.05);
        food.setCanxi((float)0.0);
        food.setIron((float)3.1);
        food.setZinc((float)4.55);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)242.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Thịt kho tiêu");
        food.setImage("https://nauankhongkho.com/wp-content/uploads/2016/12/cach-lam-thit-kho-tieu-ngon-chuan-vi-me-nau.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)11.5);
        food.setProtein((float)21.2);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.17);
        food.setCanxi((float)9.8);
        food.setIron((float)1.4);
        food.setZinc((float)3.5);
        food.setVitaminA((float)2.8);
        food.setVitaminB((float)1260.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)200.0);
        food.setWeight((float)140.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Thịt kho trứng");
        food.setImage("https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/news/2018/07/15/cach-lam-mon-thit-kho-trung-ngon-quen-sau-095733.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)7.5);
        food.setProtein((float)19.8);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)53.5);
        food.setIron((float)6.1);
        food.setZinc((float)3.5);
        food.setVitaminA((float)224.0);
        food.setVitaminB((float)300.0);
        food.setVitaminC((float)500.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)315.0);
        food.setWeight((float)120.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Cơm chiên dương châu");
        food.setImage("https://ameovat.com/wp-content/uploads/2016/05/cach-lam-com-chien-duong-chau-600x481.jpg");
        food.setPrice((float)25000.0);
        food.setCarbonhydrates((float)92.7);
        food.setProtein((float)14.9);
        food.setLipid((float)0.0);
        food.setXenluloza((float)1.56);
        food.setCanxi((float)42.3);
        food.setIron((float)1.6);
        food.setZinc((float)0.0);
        food.setVitaminA((float)156.0);
        food.setVitaminB((float)330.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)1.01);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)530.0);
        food.setWeight((float)650.0);
        food.setStatus(1);
        food.setPrice((float)25000.0);
        foods.add(food);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("1 Đĩa Cơm Trắng");
        food.setImage("http://files.vforum.vn/2014/T12/img/vforum.vn-141202-chien-com.jpg");
        food.setPrice((float)1500.0);
        food.setCarbonhydrates((float)84.4);
        food.setProtein((float)8.0);
        food.setLipid((float)0.8);
        food.setXenluloza((float)4.0);
        food.setCanxi((float)8.0);
        food.setIron((float)0.4);
        food.setZinc((float)1.6);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)388.0);
        food.setWeight((float)400.0);
        food.setStatus(1);
        food.setPrice((float)1500.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Đùi gà chiên");
        food.setImage("https://muoihaicunghoangdao.com/wp-content/uploads/2018/12/%C4%91%C3%B9i-g%C3%A0-chi%C3%AAn-n%C6%B0%E1%BB%9Bc-m%E1%BA%AFm-2.jpg");
        food.setPrice((float)4000.0);
        food.setCarbonhydrates((float)4.6);
        food.setProtein((float)11.0);
        food.setLipid((float)12.3);
        food.setXenluloza((float)0.6);
        food.setCanxi((float)8.35);
        food.setIron((float)1.04);
        food.setZinc((float)0.0);
        food.setVitaminA((float)83.4);
        food.setVitaminB((float)278.4);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)2784.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)173.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)4000.0);
        foods.add(food);

        categoryIds.add((long) 3);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Đậu hũ sốt cà chua");
        food.setImage("https://media.cooky.vn/recipe/g5/45672/s800x500/cooky-recipe-cover-r45672.jpg");
        food.setPrice((float)4000.0);
        food.setCarbonhydrates((float)11.0);
        food.setProtein((float)18.1);
        food.setLipid((float)13.6);
        food.setXenluloza((float)1.44);
        food.setCanxi((float)36.0);
        food.setIron((float)9.15);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)40.0);
        food.setVitaminC((float)24000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)239.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)4000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Chả trứng chưng");
        food.setImage("https://i.ytimg.com/vi/4p-MXktcMqE/hqdefault.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)9.4);
        food.setProtein((float)10.8);
        food.setLipid((float)5.1);
        food.setXenluloza((float)0.72);
        food.setCanxi((float)22.52);
        food.setIron((float)0.66);
        food.setZinc((float)0.0);
        food.setVitaminA((float)62.4);
        food.setVitaminB((float)40.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)1.5);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)127.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 2);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Tôm lăn bột chiên");
        food.setImage("https://daynauan.info.vn/wp-content/uploads/2016/11/tom-tam-bot-chien-xu.jpg");
        food.setPrice((float)10000.0);
        food.setCarbonhydrates((float)36.3);
        food.setProtein((float)2.6);
        food.setLipid((float)10.1);
        food.setXenluloza((float)0.51);
        food.setCanxi((float)68.75);
        food.setIron((float)4.48);
        food.setZinc((float)0.0);
        food.setVitaminA((float)60.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)247.0);
        food.setWeight((float)300.0);
        food.setStatus(1);
        food.setPrice((float)10000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Chuối tiêu");
        food.setImage("http://anchaysongkhoe.com/wp-content/uploads/2019/08/Gi%C3%A1-tr%E1%BB%8B-dinh-d%C6%B0%E1%BB%A1ng-c%E1%BB%A7a-chu%E1%BB%91i-650x381.jpg");
        food.setPrice((float)1000.0);
        food.setCarbonhydrates((float)19.98);
        food.setProtein((float)1.35);
        food.setLipid((float)0.18);
        food.setXenluloza((float)0.572);
        food.setCanxi((float)7.2);
        food.setIron((float)0.54);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)90.0);
        food.setVitaminC((float)5400.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)90.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)1000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Chuối tây");
        food.setImage("http://hn.check.net.vn/Data/product/mainimages/original/product6105.jpg");
        food.setPrice((float)1000.0);
        food.setCarbonhydrates((float)15.0);
        food.setProtein((float)0.9);
        food.setLipid((float)0.3);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)12.0);
        food.setIron((float)0.5);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)6000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)66.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)1000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Bưởi");
        food.setImage("https://yhocdinhduong.vn/images/baiviet/tong_quan_suc_khoe/cong-dung-va-gia-tri-dinh-duong-cua-qua-buoi-2.jpg");
        food.setPrice((float)1500.0);
        food.setCarbonhydrates((float)7.3);
        food.setProtein((float)0.2);
        food.setLipid((float)0.04);
        food.setXenluloza((float)0.7);
        food.setCanxi((float)230.0);
        food.setIron((float)50.0);
        food.setZinc((float)0.08);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)317.0);
        food.setVitaminC((float)61000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)30.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)1500.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Chôm chôm");
        food.setImage("https://3.bp.blogspot.com/-TQiToKSLIQk/VhU-FXR3SRI/AAAAAAAACsc/zKzA4lnW5fA/s1600/1438761299-babauanchomchom2.jpg");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)16.4);
        food.setProtein((float)1.5);
        food.setLipid((float)0.0);
        food.setXenluloza((float)1.3);
        food.setCanxi((float)27.8);
        food.setIron((float)0.5);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)72.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Dưa hấu");
        food.setImage("https://cdn.24h.com.vn/upload/2-2019/images/2019-06-24/Nhung-nguoi-nay-an-dua-hau-co-the-mat-mang-1-1561353989-337-width640height360.jpg");
        food.setPrice((float)1000.0);
        food.setCarbonhydrates((float)2.3);
        food.setProtein((float)1.2);
        food.setLipid((float)200.0);
        food.setXenluloza((float)500.0);
        food.setCanxi((float)8.0);
        food.setIron((float)0.1);
        food.setZinc((float)0.0);
        food.setVitaminA((float)170.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)7000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)16.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)1000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Mít dai");
        food.setImage("http://st.suckhoegiadinh.com.vn/staticFile/Subject/2018/06/23/hinh-1_231729429.jpg");
        food.setPrice((float)3000.0);
        food.setCarbonhydrates((float)11.4);
        food.setProtein((float)0.6);
        food.setLipid((float)0.0);
        food.setXenluloza((float)1.2);
        food.setCanxi((float)21.0);
        food.setIron((float)0.4);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)5000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)48.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)3000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Na");
        food.setImage("https://nongsandungha.com/wp-content/uploads/hi.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)39.0);
        food.setProtein((float)3.2);
        food.setLipid((float)0.0);
        food.setXenluloza((float)1.6);
        food.setCanxi((float)70.0);
        food.setIron((float)1.2);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)400.0);
        food.setVitaminC((float)72000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)128.0);
        food.setWeight((float)200.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Xoài chín");
        food.setImage("https://znews-photo.zadn.vn/w660/Uploaded/sgorvz/2016_06_14/qua_xoai_1.jpg");
        food.setPrice((float)5000.0);
        food.setCarbonhydrates((float)315.9);
        food.setProtein((float)0.6);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)0.4);
        food.setIron((float)0.0);
        food.setZinc((float)0.56);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)200.0);
        food.setVitaminC((float)30000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)1120.0);
        food.setCalorie((float)69.0);
        food.setWeight((float)100.0);
        food.setStatus(1);
        food.setPrice((float)5000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Nước cam vắt");
        food.setImage("https://cdn.jamja.vn/blog/wp-content/uploads/2018/08/gia-tri-dinh-duong-ua-nuoc-cam.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)55.7);
        food.setProtein((float)0.9);
        food.setLipid((float)0.0);
        food.setXenluloza((float)8.4);
        food.setCanxi((float)34.0);
        food.setIron((float)0.4);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)100.0);
        food.setVitaminC((float)40000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)226.0);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Nước chanh");
        food.setImage("http://nauanchobe.vn/upload/image/2016/08/12/nuoc-chanh-tuoi-nguoi-ban-than-thiet-cua-ba-bau_2016-08-12-155624.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)8.64);
        food.setProtein((float)0.9);
        food.setLipid((float)0.0);
        food.setXenluloza((float)2.34);
        food.setCanxi((float)72.0);
        food.setIron((float)1.08);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)40000.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)149.0);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Nước sâm");
        food.setImage("https://znews-photo.zadn.vn/w660/Uploaded/lepx/2016_04_20/anh2.jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)19.9);
        food.setProtein((float)0.0);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)72.0);
        food.setIron((float)1.08);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)74.0);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        food = new Food();
        food.setCategories(categorySet);
        food.setName("Nước mía");
        food.setImage("https://media.ex-cdn.com/EXP/media.cpcs.vn/files/f1/maxresdefault%20(1)(60).jpg");
        food.setPrice((float)8000.0);
        food.setCarbonhydrates((float)73.0);
        food.setProtein((float)0.0);
        food.setLipid((float)0.0);
        food.setXenluloza((float)0.0);
        food.setCanxi((float)23.4);
        food.setIron((float)6.48);
        food.setZinc((float)0.0);
        food.setVitaminA((float)0.0);
        food.setVitaminB((float)0.0);
        food.setVitaminC((float)0.0);
        food.setVitaminD((float)0.0);
        food.setVitaminE((float)0.0);
        food.setCalorie((float)106.0);
        food.setWeight((float)180.0);
        food.setStatus(1);
        food.setPrice((float)8000.0);
        foods.add(food);

        foodRepository.saveAll(foods);
    }

    private void seedingCombo () {
        Combo combo;
        List<Category> categoryList;
        List<Long> categoryIds = new ArrayList<>();
        Set<Category> categorySet;

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        combo = new Combo();
        combo.setCategories(categorySet);
        combo.setName("Cơm sườn nướng");
        combo.setImage("https://images.foody.vn/res/g8/72063/prof/s576x330/foody-upload-api-foody-mobile-9-190502170442.jpg");
        combo.setPrice((float)19500.0);
        combo.setCarbonhydrates((float)0.0);
        combo.setProtein((float)0.0);
        combo.setLipid((float)0.0);
        combo.setXenluloza((float)0.0);
        combo.setCanxi((float)0.0);
        combo.setIron((float)0.0);
        combo.setZinc((float)0.0);
        combo.setVitaminA((float)0.0);
        combo.setVitaminB((float)0.0);
        combo.setVitaminC((float)0.0);
        combo.setVitaminD((float)0.0);
        combo.setVitaminE((float)0.0);
        combo.setCalorie((float)0.0);
        combo.setWeight((float)0.0);
        combo.setStatus(1);
        combo.setPrice((float)19500.0);
        combos.add(combo);

        categoryIds.add((long) 2);

        categoryIds.add((long) 3);

        categoryIds.add((long) 4);

        categoryIds.add((long) 1);

        categoryIds.add((long) 1);

        categoryList = categoryRepository.findAllByIdIn(categoryIds);
        categorySet = new HashSet<>(categoryList);
        combo = new Combo();
        combo.setCategories(categorySet);
        combo.setName("Cơm sườn nướng");
        combo.setImage("https://beptruong.edu.vn/wp-content/uploads/2018/06/cach-uop-thit-nuong-com-tam.jpg");
        combo.setPrice((float)19500.0);
        combo.setCarbonhydrates((float)22.3);
        combo.setProtein((float)36.9);
        combo.setLipid((float)7.35);
        combo.setXenluloza((float)0.18);
        combo.setCanxi((float)236.2);
        combo.setIron((float)4.7);
        combo.setZinc((float)5.66);
        combo.setVitaminA((float)118.8);
        combo.setVitaminB((float)3756.0);
        combo.setVitaminC((float)11700.0);
        combo.setVitaminD((float)2.01);
        combo.setVitaminE((float)720.0);
        combo.setCalorie((float)452.66);
        combo.setWeight((float)470.0);
        combo.setStatus(1);
        combo.setPrice((float)19500.0);
        combos.add(combo);

        comboRepository.saveAll(combos);
    }

}
 