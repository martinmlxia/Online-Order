package com.conant.order.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.conant.order.dao.RegisterDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;

public class RegisterDaoIMP implements RegisterDao {

	private static Logger log = Logger.getLogger("RegisterDaoIMP",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException {
		this.template = template;
	}

	//@Override
	public void insertUserDeptInfo(String loginId, UserDeptInfo userDeptInfo)
			throws ProcessException {
		// TODO Auto-generated method stub
		try {
			if (null != userDeptInfo) {
				template.save(userDeptInfo);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	//@Override
	public void insertUserInfo(String loginId, UserInfo userInfo)
			throws ProcessException {
		// TODO Auto-generated method stub
		try {
			if (null != userInfo) {
				template.save(userInfo);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	//@Override
	public void insertUserRoleInfo(String loginId, UserRoleInfo userRoleInfo)
			throws ProcessException {
		// TODO Auto-generated method stub
		try {
			if (null != userRoleInfo) {
				template.save(userRoleInfo);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	//@Override
	public String getMaxUserId(String loginId) throws ProcessException {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		ArrayList userIdList = new ArrayList();
		String user_id = "";

		List list = template.find("from UserInfo userInfo");

		if (null != list) {
			// System.out.println(list.size() + "sdfasdfasfasf");
			for (int i = 0; i < list.size(); i++) {
				userInfo = (UserInfo) list.get(i);
				userIdList.add(userInfo.getUser_id());
			}
			if (null != userIdList) {
				user_id = (String) userIdList.get(0);
				for (int j = 0; j < userIdList.size(); j++) {
					if (Integer.parseInt(user_id) < Integer
							.parseInt(((String) userIdList.get(j))))
						;
					user_id = (String) userIdList.get(j);
				}
			}
		}

		return String.valueOf(Integer.parseInt(user_id) + 1);

	}

	public String getMaxUserCode(String loginId) throws ProcessException {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		ArrayList userCodeList = new ArrayList();
		String user_code = "";

		List list = template.find("from UserInfo userInfo");

		if (null != list && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				userInfo = (UserInfo) list.get(i);
				if (null != userInfo.getUser_code()
						&& !"".equals(userInfo.getUser_code())) {
					userCodeList.add(userInfo.getUser_code());
				}
			}
			if (null != userCodeList && userCodeList.size() > 0) {
				user_code = (String) userCodeList.get(0);
				for (int j = 0; j < userCodeList.size(); j++) {
					if (Integer.parseInt(user_code) < Integer
							.parseInt(((String) userCodeList.get(j))))
						;
					user_code = (String) userCodeList.get(j);
				}
			} else {
				user_code = "000000";
			}
		}

		return String.valueOf(Integer.parseInt(user_code) + 1);

	}

	//@Override
	public void registerUser(String loginId, UserInfo userInfo,
			UserDeptInfo userDeptInfo, UserRoleInfo userRoleInfo)
			throws ProcessException {
		// TODO Auto-generated method stub

		try {
			if (null != userRoleInfo && null != userInfo
					&& null != userDeptInfo) {

				String user_id = this.getMaxUserId(loginId);
				String user_code = this.getMaxUserCode(loginId);
				while (user_code.length() < 6) {
					user_code = "0" + user_code;
				}
				userInfo.setUser_id(user_id);
				userInfo.setUser_code(user_code);
				userDeptInfo.setUser_id(user_id);
				userRoleInfo.setUser_id(user_id);

				template.save(userInfo);
				template.save(userDeptInfo);
				template.save(userRoleInfo);

			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public UserInfo getUserInfo(String loginId) throws ProcessException {
		// TODO Auto-generated method stub

		try {
			if (null != loginId && !"".equals(loginId)) {

				UserInfo userInfo = null;
				String hql = "from UserInfo u where u.user_id = '" + loginId
						+ "'";
				List list = template.find(hql);
				if (list.size() == 1) {
					userInfo = (UserInfo) list.get(0);
				}
				return userInfo;

			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	//@Override
	public UserInfoListQuerier queryUserInfoList(String longId,
			UserInfoListQuerier querier) throws ProcessException {
		// TODO Auto-generated method stub
		DetachedCriteria queryCriteria = DetachedCriteria
				.forClass(UserInfo.class);
		DetachedCriteria countCriteria = DetachedCriteria
				.forClass(UserInfo.class);

		try {
			if (querier == null) {
				throw new ProcessException(217001);
			}
			// query by field
			// by id
			if (querier.getUser_id() != null) {
				queryCriteria.add(Restrictions.eq("user_id", querier
						.getUser_id()));
				countCriteria.add(Restrictions.eq("user_id", querier
						.getUser_id()));
			}

//			if (querier.getIs_audit() != null) {
//				queryCriteria.add(Restrictions.eq("is_audit", querier
//						.getIs_audit()));		
//				countCriteria.add(Restrictions.eq("is_audit", querier
//						.getIs_audit()));
//			}

			// query total count
			Session session = template.getSessionFactory().openSession();
			int recordCount = ((Integer) countCriteria.getExecutableCriteria(
					session).setProjection(Projections.rowCount())
					.uniqueResult()).intValue();
			session.close();
			log.debugT("RegisterDaoIMP: totalCount === " + recordCount);
			// order by
			if (querier.getOrders() == null) {
				Order userInfoOrder = new HbOrder("user_id", false);
				querier.setOrders(new Order[] { userInfoOrder });
			}
			for (Order order : querier.getOrders()) {
				if (order != null) {
					queryCriteria.addOrder(order);
				}
			}
			// pagination
			if (querier.getStartIndex() == null) {
				querier.setStartIndex(0);
			}
			// -1 for all records
			if (querier.getPageSize() == -1) {
				querier.setPageSize(recordCount);
			}
			// default 20
			if (querier.getPageSize() == null || querier.getPageSize() == 0) {
				querier.setPageSize(20);
			}
			List list = template.findByCriteria(queryCriteria, querier
					.getStartIndex(), querier.getPageSize());
			querier.setListUser(list);
			querier.setRecordCount(recordCount);

			if (recordCount >= 0) {
				if (querier.getPageSize() > querier.getRecordCount()) {
					querier.setPageCount(1);
				} else {
					int pageCount = recordCount / querier.getPageSize();
					if (recordCount % querier.getPageSize() != 0) {
						pageCount++;
					}
					querier.setPageCount(pageCount);
				}
			}
			return querier;
		} catch (Exception e) {
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	//@Override
	public void updateUserInfo(String loginId, UserInfo userInfo)
			throws ProcessException {
		// TODO Auto-generated method stub
		try {
			if (null != userInfo) {
				template.update(userInfo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteUserInfos(Integer[] userIds) throws ProcessException {
		try {
			if (userIds != null && userIds.length > 0) {
				List<UserInfo> listUser = new ArrayList<UserInfo>();
				for (Integer userId : userIds) {
					// 存在级联关系的实体删除前需要先load
					UserInfo userInfo = getUserInfo(String.valueOf(userId));
					if (userInfo != null) {
						listUser.add((UserInfo) userInfo);
					}
				}
				template.deleteAll(listUser);
			} else {
				throw new ProcessException(400021);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public List getUserList() throws ProcessException {
		try {
			List list = template.loadAll(UserInfo.class);
			return list;
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	@SuppressWarnings("null")
	public List<UserInfo> getSalesList() throws ProcessException {

		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo userInfo = null;

		try {
			List tempList = template.loadAll(UserInfo.class);
			if (null != tempList && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					userInfo = (UserInfo) tempList.get(i);
					if (!"000000000000001".equals(userInfo.getUser_id())) {
						if ("0000000002"
								.equals(getRoleId(userInfo.getUser_id()))
								|| "0000000008".equals(getRoleId(userInfo
										.getUser_id()))
								|| "0000000010".equals(getRoleId(userInfo
										.getUser_id()))
								|| "0000000011".equals(getRoleId(userInfo
										.getUser_id()))
								|| "0000000012".equals(getRoleId(userInfo
										.getUser_id()))) {
							list.add(userInfo);
						}
					}
				}
			}
			return list;
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public List<UserInfo> getProductList() throws ProcessException {

		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo userInfo = null;

		try {
			List tempList = template.loadAll(UserInfo.class);
			if (null != tempList && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					userInfo = (UserInfo) tempList.get(i);
					if (!"000000000000001".equals(userInfo.getUser_id())) {
						if ("0000000007"
								.equals(getRoleId(userInfo.getUser_id()))
								|| "0000000005".equals(getRoleId(userInfo
										.getUser_id()))) {
							list.add(userInfo);
						}
					}
				}
			}
			return list;
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public String getRoleId(String user_id) throws ProcessException {

		try {
			if (null != user_id && !"".equals(user_id)) {

				UserRoleInfo userRoleInfo = null;
				String hql = "from UserRoleInfo u where u.user_id = '"
						+ user_id + "'";
				List list = template.find(hql);
				if (list.size() == 1) {
					userRoleInfo = (UserRoleInfo) list.get(0);
				}
				if(userRoleInfo==null) return null;
				else return userRoleInfo.getRole_id();
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public String getSaleOwnerId(String user_id) throws ProcessException {

		try {
			if (null != user_id && !"".equals(user_id)) {

				UserInfo userInfo = null;
				String hql = "from UserInfo u where u.user_id = '" + user_id
						+ "'";
				List list = template.find(hql);
				if (list.size() == 1) {
					userInfo = (UserInfo) list.get(0);
				}
				return userInfo.getSaleOwner();

			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public String getProductOwnerId(String user_id) throws ProcessException {

		try {
			if (null != user_id && !"".equals(user_id)) {

				UserInfo userInfo = null;
				String hql = "from UserInfo u where u.user_id = '" + user_id
						+ "'";
				List list = template.find(hql);
				if (list.size() == 1) {
					userInfo = (UserInfo) list.get(0);
				}
				return userInfo.getProductOwner();

			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}
}
