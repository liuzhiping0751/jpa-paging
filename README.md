# jpa-paging
springBoot jpa Bootstarp分页器 layui分页

后台使用springBoot，前端写了几个简单的分页，分别使用了bootstrap-paging 和layui分页、数据表格分页。。
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
Bootstrap分页
![Bootstrappaging](https://github.com/liuzhiping0751/jpa-paging/raw/master/project-description-image/bootstrap_paging.jpg)
layui 分页工具分页
![layuipaging](https://github.com/liuzhiping0751/jpa-paging/raw/master/project-description-image/layui_paging.jpg)
layui表格分页
![layuipaging](https://github.com/liuzhiping0751/jpa-paging/raw/master/project-description-image/layui_table_paging.jpg)

数据库数据
----
```Sql
/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : tk_test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 09/01/2020 16:06:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `money` double NULL DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '老王', 15, 5000);
INSERT INTO `user` VALUES (2, '小李子', 50, 2000);
INSERT INTO `user` VALUES (3, '山大王', 50, 2000);
INSERT INTO `user` VALUES (5, '角度讲呢', 50, 2000);
INSERT INTO `user` VALUES (6, '王大锤', 10, 2000);
INSERT INTO `user` VALUES (7, '铁锤s', 20, 3000);
INSERT INTO `user` VALUES (8, 'dsf', 20, 6000);
INSERT INTO `user` VALUES (9, 'lmv', 32, 7000);
INSERT INTO `user` VALUES (10, 'iebnf', 12, 500);
INSERT INTO `user` VALUES (11, 'fsdf', 50, 9000);
INSERT INTO `user` VALUES (12, 'jkjf', 20, 4000);
INSERT INTO `user` VALUES (13, 'lbk', 19, 3000);

SET FOREIGN_KEY_CHECKS = 1;
```
