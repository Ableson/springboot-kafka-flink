package com.dtinone.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResManagementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResManagementExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdKeyIsNull() {
            addCriterion("id_key is null");
            return (Criteria) this;
        }

        public Criteria andIdKeyIsNotNull() {
            addCriterion("id_key is not null");
            return (Criteria) this;
        }

        public Criteria andIdKeyEqualTo(String value) {
            addCriterion("id_key =", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyNotEqualTo(String value) {
            addCriterion("id_key <>", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyGreaterThan(String value) {
            addCriterion("id_key >", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("id_key >=", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyLessThan(String value) {
            addCriterion("id_key <", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyLessThanOrEqualTo(String value) {
            addCriterion("id_key <=", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyLike(String value) {
            addCriterion("id_key like", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyNotLike(String value) {
            addCriterion("id_key not like", value, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyIn(List<String> values) {
            addCriterion("id_key in", values, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyNotIn(List<String> values) {
            addCriterion("id_key not in", values, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyBetween(String value1, String value2) {
            addCriterion("id_key between", value1, value2, "idKey");
            return (Criteria) this;
        }

        public Criteria andIdKeyNotBetween(String value1, String value2) {
            addCriterion("id_key not between", value1, value2, "idKey");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNull() {
            addCriterion("sys_name is null");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNotNull() {
            addCriterion("sys_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysNameEqualTo(String value) {
            addCriterion("sys_name =", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotEqualTo(String value) {
            addCriterion("sys_name <>", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThan(String value) {
            addCriterion("sys_name >", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_name >=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThan(String value) {
            addCriterion("sys_name <", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThanOrEqualTo(String value) {
            addCriterion("sys_name <=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLike(String value) {
            addCriterion("sys_name like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotLike(String value) {
            addCriterion("sys_name not like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameIn(List<String> values) {
            addCriterion("sys_name in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotIn(List<String> values) {
            addCriterion("sys_name not in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameBetween(String value1, String value2) {
            addCriterion("sys_name between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotBetween(String value1, String value2) {
            addCriterion("sys_name not between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNull() {
            addCriterion("use_type is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("use_type is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(String value) {
            addCriterion("use_type =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(String value) {
            addCriterion("use_type <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(String value) {
            addCriterion("use_type >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("use_type >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(String value) {
            addCriterion("use_type <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(String value) {
            addCriterion("use_type <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLike(String value) {
            addCriterion("use_type like", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotLike(String value) {
            addCriterion("use_type not like", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<String> values) {
            addCriterion("use_type in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<String> values) {
            addCriterion("use_type not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(String value1, String value2) {
            addCriterion("use_type between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(String value1, String value2) {
            addCriterion("use_type not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andNetworkNameIsNull() {
            addCriterion("network_name is null");
            return (Criteria) this;
        }

        public Criteria andNetworkNameIsNotNull() {
            addCriterion("network_name is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkNameEqualTo(String value) {
            addCriterion("network_name =", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameNotEqualTo(String value) {
            addCriterion("network_name <>", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameGreaterThan(String value) {
            addCriterion("network_name >", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameGreaterThanOrEqualTo(String value) {
            addCriterion("network_name >=", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameLessThan(String value) {
            addCriterion("network_name <", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameLessThanOrEqualTo(String value) {
            addCriterion("network_name <=", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameLike(String value) {
            addCriterion("network_name like", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameNotLike(String value) {
            addCriterion("network_name not like", value, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameIn(List<String> values) {
            addCriterion("network_name in", values, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameNotIn(List<String> values) {
            addCriterion("network_name not in", values, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameBetween(String value1, String value2) {
            addCriterion("network_name between", value1, value2, "networkName");
            return (Criteria) this;
        }

        public Criteria andNetworkNameNotBetween(String value1, String value2) {
            addCriterion("network_name not between", value1, value2, "networkName");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRemoveIsNull() {
            addCriterion("remove is null");
            return (Criteria) this;
        }

        public Criteria andRemoveIsNotNull() {
            addCriterion("remove is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveEqualTo(Integer value) {
            addCriterion("remove =", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotEqualTo(Integer value) {
            addCriterion("remove <>", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveGreaterThan(Integer value) {
            addCriterion("remove >", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveGreaterThanOrEqualTo(Integer value) {
            addCriterion("remove >=", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveLessThan(Integer value) {
            addCriterion("remove <", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveLessThanOrEqualTo(Integer value) {
            addCriterion("remove <=", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveIn(List<Integer> values) {
            addCriterion("remove in", values, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotIn(List<Integer> values) {
            addCriterion("remove not in", values, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveBetween(Integer value1, Integer value2) {
            addCriterion("remove between", value1, value2, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotBetween(Integer value1, Integer value2) {
            addCriterion("remove not between", value1, value2, "remove");
            return (Criteria) this;
        }

        public Criteria andSystemTypeIsNull() {
            addCriterion("system_type is null");
            return (Criteria) this;
        }

        public Criteria andSystemTypeIsNotNull() {
            addCriterion("system_type is not null");
            return (Criteria) this;
        }

        public Criteria andSystemTypeEqualTo(String value) {
            addCriterion("system_type =", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeNotEqualTo(String value) {
            addCriterion("system_type <>", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeGreaterThan(String value) {
            addCriterion("system_type >", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("system_type >=", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeLessThan(String value) {
            addCriterion("system_type <", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeLessThanOrEqualTo(String value) {
            addCriterion("system_type <=", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeLike(String value) {
            addCriterion("system_type like", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeNotLike(String value) {
            addCriterion("system_type not like", value, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeIn(List<String> values) {
            addCriterion("system_type in", values, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeNotIn(List<String> values) {
            addCriterion("system_type not in", values, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeBetween(String value1, String value2) {
            addCriterion("system_type between", value1, value2, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemTypeNotBetween(String value1, String value2) {
            addCriterion("system_type not between", value1, value2, "systemType");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyIsNull() {
            addCriterion("system_defend_company is null");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyIsNotNull() {
            addCriterion("system_defend_company is not null");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyEqualTo(String value) {
            addCriterion("system_defend_company =", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyNotEqualTo(String value) {
            addCriterion("system_defend_company <>", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyGreaterThan(String value) {
            addCriterion("system_defend_company >", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("system_defend_company >=", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyLessThan(String value) {
            addCriterion("system_defend_company <", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyLessThanOrEqualTo(String value) {
            addCriterion("system_defend_company <=", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyLike(String value) {
            addCriterion("system_defend_company like", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyNotLike(String value) {
            addCriterion("system_defend_company not like", value, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyIn(List<String> values) {
            addCriterion("system_defend_company in", values, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyNotIn(List<String> values) {
            addCriterion("system_defend_company not in", values, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyBetween(String value1, String value2) {
            addCriterion("system_defend_company between", value1, value2, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendCompanyNotBetween(String value1, String value2) {
            addCriterion("system_defend_company not between", value1, value2, "systemDefendCompany");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleIsNull() {
            addCriterion("system_defend_people is null");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleIsNotNull() {
            addCriterion("system_defend_people is not null");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleEqualTo(String value) {
            addCriterion("system_defend_people =", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleNotEqualTo(String value) {
            addCriterion("system_defend_people <>", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleGreaterThan(String value) {
            addCriterion("system_defend_people >", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("system_defend_people >=", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleLessThan(String value) {
            addCriterion("system_defend_people <", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleLessThanOrEqualTo(String value) {
            addCriterion("system_defend_people <=", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleLike(String value) {
            addCriterion("system_defend_people like", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleNotLike(String value) {
            addCriterion("system_defend_people not like", value, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleIn(List<String> values) {
            addCriterion("system_defend_people in", values, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleNotIn(List<String> values) {
            addCriterion("system_defend_people not in", values, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleBetween(String value1, String value2) {
            addCriterion("system_defend_people between", value1, value2, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andSystemDefendPeopleNotBetween(String value1, String value2) {
            addCriterion("system_defend_people not between", value1, value2, "systemDefendPeople");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneIsNull() {
            addCriterion("defend_phone is null");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneIsNotNull() {
            addCriterion("defend_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneEqualTo(String value) {
            addCriterion("defend_phone =", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneNotEqualTo(String value) {
            addCriterion("defend_phone <>", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneGreaterThan(String value) {
            addCriterion("defend_phone >", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("defend_phone >=", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneLessThan(String value) {
            addCriterion("defend_phone <", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneLessThanOrEqualTo(String value) {
            addCriterion("defend_phone <=", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneLike(String value) {
            addCriterion("defend_phone like", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneNotLike(String value) {
            addCriterion("defend_phone not like", value, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneIn(List<String> values) {
            addCriterion("defend_phone in", values, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneNotIn(List<String> values) {
            addCriterion("defend_phone not in", values, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneBetween(String value1, String value2) {
            addCriterion("defend_phone between", value1, value2, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andDefendPhoneNotBetween(String value1, String value2) {
            addCriterion("defend_phone not between", value1, value2, "defendPhone");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryIsNull() {
            addCriterion("system_summary is null");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryIsNotNull() {
            addCriterion("system_summary is not null");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryEqualTo(String value) {
            addCriterion("system_summary =", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryNotEqualTo(String value) {
            addCriterion("system_summary <>", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryGreaterThan(String value) {
            addCriterion("system_summary >", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("system_summary >=", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryLessThan(String value) {
            addCriterion("system_summary <", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryLessThanOrEqualTo(String value) {
            addCriterion("system_summary <=", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryLike(String value) {
            addCriterion("system_summary like", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryNotLike(String value) {
            addCriterion("system_summary not like", value, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryIn(List<String> values) {
            addCriterion("system_summary in", values, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryNotIn(List<String> values) {
            addCriterion("system_summary not in", values, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryBetween(String value1, String value2) {
            addCriterion("system_summary between", value1, value2, "systemSummary");
            return (Criteria) this;
        }

        public Criteria andSystemSummaryNotBetween(String value1, String value2) {
            addCriterion("system_summary not between", value1, value2, "systemSummary");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}