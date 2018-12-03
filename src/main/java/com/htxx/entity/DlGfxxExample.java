package com.htxx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DlGfxxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlGfxxExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIsNull() {
            addCriterion("enterprise_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIsNotNull() {
            addCriterion("enterprise_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdEqualTo(Long value) {
            addCriterion("enterprise_id =", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotEqualTo(Long value) {
            addCriterion("enterprise_id <>", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdGreaterThan(Long value) {
            addCriterion("enterprise_id >", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enterprise_id >=", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdLessThan(Long value) {
            addCriterion("enterprise_id <", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdLessThanOrEqualTo(Long value) {
            addCriterion("enterprise_id <=", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIn(List<Long> values) {
            addCriterion("enterprise_id in", values, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotIn(List<Long> values) {
            addCriterion("enterprise_id not in", values, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdBetween(Long value1, Long value2) {
            addCriterion("enterprise_id between", value1, value2, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotBetween(Long value1, Long value2) {
            addCriterion("enterprise_id not between", value1, value2, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andGfmcIsNull() {
            addCriterion("gfmc is null");
            return (Criteria) this;
        }

        public Criteria andGfmcIsNotNull() {
            addCriterion("gfmc is not null");
            return (Criteria) this;
        }

        public Criteria andGfmcEqualTo(String value) {
            addCriterion("gfmc =", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcNotEqualTo(String value) {
            addCriterion("gfmc <>", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcGreaterThan(String value) {
            addCriterion("gfmc >", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcGreaterThanOrEqualTo(String value) {
            addCriterion("gfmc >=", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcLessThan(String value) {
            addCriterion("gfmc <", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcLessThanOrEqualTo(String value) {
            addCriterion("gfmc <=", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcLike(String value) {
            addCriterion("gfmc like", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcNotLike(String value) {
            addCriterion("gfmc not like", value, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcIn(List<String> values) {
            addCriterion("gfmc in", values, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcNotIn(List<String> values) {
            addCriterion("gfmc not in", values, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcBetween(String value1, String value2) {
            addCriterion("gfmc between", value1, value2, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfmcNotBetween(String value1, String value2) {
            addCriterion("gfmc not between", value1, value2, "gfmc");
            return (Criteria) this;
        }

        public Criteria andGfshIsNull() {
            addCriterion("gfsh is null");
            return (Criteria) this;
        }

        public Criteria andGfshIsNotNull() {
            addCriterion("gfsh is not null");
            return (Criteria) this;
        }

        public Criteria andGfshEqualTo(String value) {
            addCriterion("gfsh =", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshNotEqualTo(String value) {
            addCriterion("gfsh <>", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshGreaterThan(String value) {
            addCriterion("gfsh >", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshGreaterThanOrEqualTo(String value) {
            addCriterion("gfsh >=", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshLessThan(String value) {
            addCriterion("gfsh <", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshLessThanOrEqualTo(String value) {
            addCriterion("gfsh <=", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshLike(String value) {
            addCriterion("gfsh like", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshNotLike(String value) {
            addCriterion("gfsh not like", value, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshIn(List<String> values) {
            addCriterion("gfsh in", values, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshNotIn(List<String> values) {
            addCriterion("gfsh not in", values, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshBetween(String value1, String value2) {
            addCriterion("gfsh between", value1, value2, "gfsh");
            return (Criteria) this;
        }

        public Criteria andGfshNotBetween(String value1, String value2) {
            addCriterion("gfsh not between", value1, value2, "gfsh");
            return (Criteria) this;
        }

        public Criteria andDzdhIsNull() {
            addCriterion("dzdh is null");
            return (Criteria) this;
        }

        public Criteria andDzdhIsNotNull() {
            addCriterion("dzdh is not null");
            return (Criteria) this;
        }

        public Criteria andDzdhEqualTo(String value) {
            addCriterion("dzdh =", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhNotEqualTo(String value) {
            addCriterion("dzdh <>", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhGreaterThan(String value) {
            addCriterion("dzdh >", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhGreaterThanOrEqualTo(String value) {
            addCriterion("dzdh >=", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhLessThan(String value) {
            addCriterion("dzdh <", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhLessThanOrEqualTo(String value) {
            addCriterion("dzdh <=", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhLike(String value) {
            addCriterion("dzdh like", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhNotLike(String value) {
            addCriterion("dzdh not like", value, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhIn(List<String> values) {
            addCriterion("dzdh in", values, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhNotIn(List<String> values) {
            addCriterion("dzdh not in", values, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhBetween(String value1, String value2) {
            addCriterion("dzdh between", value1, value2, "dzdh");
            return (Criteria) this;
        }

        public Criteria andDzdhNotBetween(String value1, String value2) {
            addCriterion("dzdh not between", value1, value2, "dzdh");
            return (Criteria) this;
        }

        public Criteria andYhzhIsNull() {
            addCriterion("yhzh is null");
            return (Criteria) this;
        }

        public Criteria andYhzhIsNotNull() {
            addCriterion("yhzh is not null");
            return (Criteria) this;
        }

        public Criteria andYhzhEqualTo(String value) {
            addCriterion("yhzh =", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhNotEqualTo(String value) {
            addCriterion("yhzh <>", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhGreaterThan(String value) {
            addCriterion("yhzh >", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhGreaterThanOrEqualTo(String value) {
            addCriterion("yhzh >=", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhLessThan(String value) {
            addCriterion("yhzh <", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhLessThanOrEqualTo(String value) {
            addCriterion("yhzh <=", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhLike(String value) {
            addCriterion("yhzh like", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhNotLike(String value) {
            addCriterion("yhzh not like", value, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhIn(List<String> values) {
            addCriterion("yhzh in", values, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhNotIn(List<String> values) {
            addCriterion("yhzh not in", values, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhBetween(String value1, String value2) {
            addCriterion("yhzh between", value1, value2, "yhzh");
            return (Criteria) this;
        }

        public Criteria andYhzhNotBetween(String value1, String value2) {
            addCriterion("yhzh not between", value1, value2, "yhzh");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListIsNull() {
            addCriterion("zyp_white_list is null");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListIsNotNull() {
            addCriterion("zyp_white_list is not null");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListEqualTo(Byte value) {
            addCriterion("zyp_white_list =", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListNotEqualTo(Byte value) {
            addCriterion("zyp_white_list <>", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListGreaterThan(Byte value) {
            addCriterion("zyp_white_list >", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListGreaterThanOrEqualTo(Byte value) {
            addCriterion("zyp_white_list >=", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListLessThan(Byte value) {
            addCriterion("zyp_white_list <", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListLessThanOrEqualTo(Byte value) {
            addCriterion("zyp_white_list <=", value, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListIn(List<Byte> values) {
            addCriterion("zyp_white_list in", values, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListNotIn(List<Byte> values) {
            addCriterion("zyp_white_list not in", values, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListBetween(Byte value1, Byte value2) {
            addCriterion("zyp_white_list between", value1, value2, "zypWhiteList");
            return (Criteria) this;
        }

        public Criteria andZypWhiteListNotBetween(Byte value1, Byte value2) {
            addCriterion("zyp_white_list not between", value1, value2, "zypWhiteList");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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