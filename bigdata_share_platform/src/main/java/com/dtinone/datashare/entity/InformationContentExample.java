package com.dtinone.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InformationContentExample() {
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

        public Criteria andCatagoryCodeIsNull() {
            addCriterion("catagory_code is null");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeIsNotNull() {
            addCriterion("catagory_code is not null");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeEqualTo(Integer value) {
            addCriterion("catagory_code =", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeNotEqualTo(Integer value) {
            addCriterion("catagory_code <>", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeGreaterThan(Integer value) {
            addCriterion("catagory_code >", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("catagory_code >=", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeLessThan(Integer value) {
            addCriterion("catagory_code <", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeLessThanOrEqualTo(Integer value) {
            addCriterion("catagory_code <=", value, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeIn(List<Integer> values) {
            addCriterion("catagory_code in", values, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeNotIn(List<Integer> values) {
            addCriterion("catagory_code not in", values, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeBetween(Integer value1, Integer value2) {
            addCriterion("catagory_code between", value1, value2, "catagoryCode");
            return (Criteria) this;
        }

        public Criteria andCatagoryCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("catagory_code not between", value1, value2, "catagoryCode");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andProviderFirstIsNull() {
            addCriterion("provider_first is null");
            return (Criteria) this;
        }

        public Criteria andProviderFirstIsNotNull() {
            addCriterion("provider_first is not null");
            return (Criteria) this;
        }

        public Criteria andProviderFirstEqualTo(String value) {
            addCriterion("provider_first =", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstNotEqualTo(String value) {
            addCriterion("provider_first <>", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstGreaterThan(String value) {
            addCriterion("provider_first >", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstGreaterThanOrEqualTo(String value) {
            addCriterion("provider_first >=", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstLessThan(String value) {
            addCriterion("provider_first <", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstLessThanOrEqualTo(String value) {
            addCriterion("provider_first <=", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstLike(String value) {
            addCriterion("provider_first like", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstNotLike(String value) {
            addCriterion("provider_first not like", value, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstIn(List<String> values) {
            addCriterion("provider_first in", values, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstNotIn(List<String> values) {
            addCriterion("provider_first not in", values, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstBetween(String value1, String value2) {
            addCriterion("provider_first between", value1, value2, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderFirstNotBetween(String value1, String value2) {
            addCriterion("provider_first not between", value1, value2, "providerFirst");
            return (Criteria) this;
        }

        public Criteria andProviderSecondIsNull() {
            addCriterion("provider_second is null");
            return (Criteria) this;
        }

        public Criteria andProviderSecondIsNotNull() {
            addCriterion("provider_second is not null");
            return (Criteria) this;
        }

        public Criteria andProviderSecondEqualTo(String value) {
            addCriterion("provider_second =", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondNotEqualTo(String value) {
            addCriterion("provider_second <>", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondGreaterThan(String value) {
            addCriterion("provider_second >", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondGreaterThanOrEqualTo(String value) {
            addCriterion("provider_second >=", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondLessThan(String value) {
            addCriterion("provider_second <", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondLessThanOrEqualTo(String value) {
            addCriterion("provider_second <=", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondLike(String value) {
            addCriterion("provider_second like", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondNotLike(String value) {
            addCriterion("provider_second not like", value, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondIn(List<String> values) {
            addCriterion("provider_second in", values, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondNotIn(List<String> values) {
            addCriterion("provider_second not in", values, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondBetween(String value1, String value2) {
            addCriterion("provider_second between", value1, value2, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andProviderSecondNotBetween(String value1, String value2) {
            addCriterion("provider_second not between", value1, value2, "providerSecond");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkIsNull() {
            addCriterion("sheng_he_remark is null");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkIsNotNull() {
            addCriterion("sheng_he_remark is not null");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkEqualTo(String value) {
            addCriterion("sheng_he_remark =", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkNotEqualTo(String value) {
            addCriterion("sheng_he_remark <>", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkGreaterThan(String value) {
            addCriterion("sheng_he_remark >", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("sheng_he_remark >=", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkLessThan(String value) {
            addCriterion("sheng_he_remark <", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkLessThanOrEqualTo(String value) {
            addCriterion("sheng_he_remark <=", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkLike(String value) {
            addCriterion("sheng_he_remark like", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkNotLike(String value) {
            addCriterion("sheng_he_remark not like", value, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkIn(List<String> values) {
            addCriterion("sheng_he_remark in", values, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkNotIn(List<String> values) {
            addCriterion("sheng_he_remark not in", values, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkBetween(String value1, String value2) {
            addCriterion("sheng_he_remark between", value1, value2, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andShengHeRemarkNotBetween(String value1, String value2) {
            addCriterion("sheng_he_remark not between", value1, value2, "shengHeRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkIsNull() {
            addCriterion("xia_jia_remark is null");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkIsNotNull() {
            addCriterion("xia_jia_remark is not null");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkEqualTo(String value) {
            addCriterion("xia_jia_remark =", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkNotEqualTo(String value) {
            addCriterion("xia_jia_remark <>", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkGreaterThan(String value) {
            addCriterion("xia_jia_remark >", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("xia_jia_remark >=", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkLessThan(String value) {
            addCriterion("xia_jia_remark <", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkLessThanOrEqualTo(String value) {
            addCriterion("xia_jia_remark <=", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkLike(String value) {
            addCriterion("xia_jia_remark like", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkNotLike(String value) {
            addCriterion("xia_jia_remark not like", value, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkIn(List<String> values) {
            addCriterion("xia_jia_remark in", values, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkNotIn(List<String> values) {
            addCriterion("xia_jia_remark not in", values, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkBetween(String value1, String value2) {
            addCriterion("xia_jia_remark between", value1, value2, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andXiaJiaRemarkNotBetween(String value1, String value2) {
            addCriterion("xia_jia_remark not between", value1, value2, "xiaJiaRemark");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNull() {
            addCriterion("network_id is null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNotNull() {
            addCriterion("network_id is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdEqualTo(String value) {
            addCriterion("network_id =", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotEqualTo(String value) {
            addCriterion("network_id <>", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThan(String value) {
            addCriterion("network_id >", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThanOrEqualTo(String value) {
            addCriterion("network_id >=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThan(String value) {
            addCriterion("network_id <", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThanOrEqualTo(String value) {
            addCriterion("network_id <=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLike(String value) {
            addCriterion("network_id like", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotLike(String value) {
            addCriterion("network_id not like", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIn(List<String> values) {
            addCriterion("network_id in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotIn(List<String> values) {
            addCriterion("network_id not in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdBetween(String value1, String value2) {
            addCriterion("network_id between", value1, value2, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotBetween(String value1, String value2) {
            addCriterion("network_id not between", value1, value2, "networkId");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkIsNull() {
            addCriterion("bo_hui_remark is null");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkIsNotNull() {
            addCriterion("bo_hui_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkEqualTo(String value) {
            addCriterion("bo_hui_remark =", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkNotEqualTo(String value) {
            addCriterion("bo_hui_remark <>", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkGreaterThan(String value) {
            addCriterion("bo_hui_remark >", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("bo_hui_remark >=", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkLessThan(String value) {
            addCriterion("bo_hui_remark <", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkLessThanOrEqualTo(String value) {
            addCriterion("bo_hui_remark <=", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkLike(String value) {
            addCriterion("bo_hui_remark like", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkNotLike(String value) {
            addCriterion("bo_hui_remark not like", value, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkIn(List<String> values) {
            addCriterion("bo_hui_remark in", values, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkNotIn(List<String> values) {
            addCriterion("bo_hui_remark not in", values, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkBetween(String value1, String value2) {
            addCriterion("bo_hui_remark between", value1, value2, "boHuiRemark");
            return (Criteria) this;
        }

        public Criteria andBoHuiRemarkNotBetween(String value1, String value2) {
            addCriterion("bo_hui_remark not between", value1, value2, "boHuiRemark");
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