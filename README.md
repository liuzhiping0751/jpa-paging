# jpa-paging
springBoot jpa Bootstarp分页器 layui分页

后台使用springBoot前端写了几个简单的分页，分别使用了bootstrap-paging 和layui分页，数据表格分页。。
----

控制器代码⬇⬇⬇⬇⬇

```Java
    /**
     * Bootstrap前端分页
     * @return
     */
    @ResponseBody
    @GetMapping("/user/queryUser")
    public MsgData queryUser() {
        return new MsgData(userDao.findAll());
    }

    /**
     *Bootstrap分页
     * @param pageNum 当前页面
     * @param pageRow 页面数据
     * @return 数据
     */
    @ResponseBody
    @GetMapping("/user/queryList")
    public PageUtil<UserBean> queryList(Integer pageNum, Integer pageRow) {
        return  userService.setectAll(pageNum, pageRow);
    }

    /**
     *Layui分页
     * @param page 当前页面
     * @param limit 页面数据
     * @return 数据
     */
    @ResponseBody
    @GetMapping("/user/queryLay")
    public PageUtil<UserBean> queryLay(Integer page, Integer limit) {
        return userService.setectAll(page - 1, limit);
    }
```
jpa查询⬇⬇⬇⬇
```Java
@Repository
public interface UserDao extends JpaRepository<UserBean,Integer> {
}
```
impl接口实现⬇⬇⬇⬇
```Java
/**
 * @author lzp
 * @date 2020/1/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     *
     * @param pageNum 页数
     * @param pageSize 页数据大小
     * @return
     */
    @Override
    public PageUtil<UserBean> setectAll(int pageNum, int pageSize) {
        PageUtil<UserBean> pageUtil = new PageUtil<>();
        Pageable pageable = PageRequest.of(pageNum, pageSize,Sort.Direction.ASC,"userId");
        Page<UserBean> userBeans = userDao.findAll(pageable);

        //数据
        pageUtil.setContent(userBeans.getContent());

        //总页数
        pageUtil.setTotalPages(userBeans.getTotalPages());

        //总数据
        pageUtil.setTotalElements(Math.toIntExact(userBeans.getTotalElements()));

        //每页大小
        pageUtil.setPageSize(userBeans.getSize());

        //当前页码
        pageUtil.setNumber(userBeans.getNumber());

        return pageUtil;
    }
}
```
分页工具⬇⬇⬇⬇

```Java
/**
 *
 * @author lzp
 * @date 2020/1/6
 * 分页工具
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUtil<T> {

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页
     */
    private int number;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 总数据
     */
    private int totalElements;

    /**
     * 数据
     */
    private List<T> content = new ArrayList<>();
}
```

简单的页面样子
----
![baidu](https://github.com/liuzhiping0751/jpa-paging/tree/master/project-description-image/bootstrap-paging.png "")
