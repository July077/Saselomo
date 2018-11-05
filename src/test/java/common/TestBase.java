package common;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huahua.saselomo.client.dao.ClientDao;
import com.huahua.saselomo.client.dao.ClientPurHistoryDao;
import com.huahua.saselomo.client.service.ClientService;
import com.huahua.saselomo.inventory.dao.InventoryDao;
import com.huahua.saselomo.product.dao.ProductDao;
import com.huahua.saselomo.product.service.ProductService;
import com.huahua.saselomo.product.service.impl.ProductServiceImpl;
import com.huahua.saselomo.receiving.dao.ProductReceivingSingleDao;
import com.huahua.saselomo.receiving.dao.ReceivingDao;
import com.huahua.saselomo.receiving.dao.ReceivingSingleDao;
import com.huahua.saselomo.receiving.service.ReceivingSingleService;
import com.huahua.saselomo.receiving.service.impl.ReceivingServiceImpl;
import com.huahua.saselomo.receiving.service.impl.ReceivingSingleServiceImpl;
import com.huahua.saselomo.sales.dao.ClientSalesDao;
import com.huahua.saselomo.sales.dao.ProSalesSingleDao;
import com.huahua.saselomo.sales.dao.SalesDao;
import com.huahua.saselomo.sales.dao.SalesSingleDao;
import com.huahua.saselomo.sales.service.impl.SalesSingleServiceImpl;
import com.huahua.saselomo.system.dao.RoleDao;
import com.huahua.saselomo.system.dao.UserDao;
/**
 * 基础测试类,初始化测试类共同需要的
 * @author Lin·Y
 *
 */
public class TestBase {
	//获取spring容器
	protected ClassPathXmlApplicationContext ctx;
	//客户数据接口
	protected ClientDao clientDao;
	//客户购买记录数据接口
	protected ClientPurHistoryDao clientPurHistoryDao;
	//客户业务接口
	protected ClientService cs;
	//产品数据接口
	protected ProductDao productDao;
	//产品业务接口
	protected ProductService ps;
	//收货单数据接口
	protected ReceivingDao receivingDao;
	//收货单业务实现类
	protected ReceivingServiceImpl receivingService;
	//收货单子项数据接口
	protected ReceivingSingleDao receivingSingleDao;
	//收货单子项与产品对应关系接口
	protected ProductReceivingSingleDao productReceivingSingleDao;
	//库存数据层接口
	protected InventoryDao inventoryDao;
	//售货单数据层接口
	protected SalesDao salesDao;
	//客户与售货单对应关系dao层接口
	protected ClientSalesDao clientSalesDao;
	//售货单子项数据接口
	protected SalesSingleDao salesSingleDao;
	//售货单子项业务层接口
	protected SalesSingleServiceImpl salesSingleService;
	//售货单子项与产品的对应关系数据接口
	protected ProSalesSingleDao proSalesSingleDao;
	//用户dao层接口
	protected UserDao userDao;
	//角色dao层接口
	protected RoleDao roleDao;
	@Before
	public void init(){
		String mvc = "spring-mvc.xml";
		String mybatis = "spring-mybatis.xml";
		String pool = "spring-pool.xml";
		ctx = new ClassPathXmlApplicationContext(mvc,mybatis, pool);
		clientPurHistoryDao = ctx.getBean("clientPurHistoryDao",ClientPurHistoryDao.class);
		clientDao = ctx.getBean("clientDao",ClientDao.class);
		cs = (ClientService) ctx.getBean("clientServiceImpl");
		productDao = ctx.getBean("productDao", ProductDao.class);
		ps = ctx.getBean("productServiceImpl", ProductServiceImpl.class);
		receivingDao = ctx.getBean("receivingDao", ReceivingDao.class);
		receivingService = ctx.getBean("receivingServiceImpl", ReceivingServiceImpl.class);
		receivingSingleDao = ctx.getBean("receivingSingleDao", ReceivingSingleDao.class);
		productReceivingSingleDao = ctx.getBean("productReceivingSingleDao", ProductReceivingSingleDao.class);
		inventoryDao = ctx.getBean("inventoryDao", InventoryDao.class);
		salesDao = ctx.getBean("salesDao", SalesDao.class);
		clientSalesDao = ctx.getBean("clientSalesDao", ClientSalesDao.class);
		salesSingleDao = ctx.getBean("salesSingleDao", SalesSingleDao.class);
		salesSingleService = ctx.getBean("salesSingleServiceImpl", SalesSingleServiceImpl.class);
		proSalesSingleDao = ctx.getBean("proSalesSingleDao", ProSalesSingleDao.class);
		userDao = ctx.getBean("userDao", UserDao.class);
		roleDao = ctx.getBean("roleDao", RoleDao.class);
		
	}
}
