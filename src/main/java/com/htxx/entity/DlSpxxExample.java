package com.htxx.entity;

import java.util.ArrayList;
import java.util.List;

public class DlSpxxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlSpxxExample() {
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

        public Criteria andYwlxIsNull() {
            addCriterion("ywlx is null");
            return (Criteria) this;
        }

        public Criteria andYwlxIsNotNull() {
            addCriterion("ywlx is not null");
            return (Criteria) this;
        }

        public Criteria andYwlxEqualTo(Byte value) {
            addCriterion("ywlx =", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotEqualTo(Byte value) {
            addCriterion("ywlx <>", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxGreaterThan(Byte value) {
            addCriterion("ywlx >", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxGreaterThanOrEqualTo(Byte value) {
            addCriterion("ywlx >=", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxLessThan(Byte value) {
            addCriterion("ywlx <", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxLessThanOrEqualTo(Byte value) {
            addCriterion("ywlx <=", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxIn(List<Byte> values) {
            addCriterion("ywlx in", values, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotIn(List<Byte> values) {
            addCriterion("ywlx not in", values, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxBetween(Byte value1, Byte value2) {
            addCriterion("ywlx between", value1, value2, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotBetween(Byte value1, Byte value2) {
            addCriterion("ywlx not between", value1, value2, "ywlx");
            return (Criteria) this;
        }

        public Criteria andSpmcIsNull() {
            addCriterion("spmc is null");
            return (Criteria) this;
        }

        public Criteria andSpmcIsNotNull() {
            addCriterion("spmc is not null");
            return (Criteria) this;
        }

        public Criteria andSpmcEqualTo(String value) {
            addCriterion("spmc =", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcNotEqualTo(String value) {
            addCriterion("spmc <>", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcGreaterThan(String value) {
            addCriterion("spmc >", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcGreaterThanOrEqualTo(String value) {
            addCriterion("spmc >=", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcLessThan(String value) {
            addCriterion("spmc <", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcLessThanOrEqualTo(String value) {
            addCriterion("spmc <=", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcLike(String value) {
            addCriterion("spmc like", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcNotLike(String value) {
            addCriterion("spmc not like", value, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcIn(List<String> values) {
            addCriterion("spmc in", values, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcNotIn(List<String> values) {
            addCriterion("spmc not in", values, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcBetween(String value1, String value2) {
            addCriterion("spmc between", value1, value2, "spmc");
            return (Criteria) this;
        }

        public Criteria andSpmcNotBetween(String value1, String value2) {
            addCriterion("spmc not between", value1, value2, "spmc");
            return (Criteria) this;
        }

        public Criteria andShflbmIsNull() {
            addCriterion("shflbm is null");
            return (Criteria) this;
        }

        public Criteria andShflbmIsNotNull() {
            addCriterion("shflbm is not null");
            return (Criteria) this;
        }

        public Criteria andShflbmEqualTo(String value) {
            addCriterion("shflbm =", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmNotEqualTo(String value) {
            addCriterion("shflbm <>", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmGreaterThan(String value) {
            addCriterion("shflbm >", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmGreaterThanOrEqualTo(String value) {
            addCriterion("shflbm >=", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmLessThan(String value) {
            addCriterion("shflbm <", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmLessThanOrEqualTo(String value) {
            addCriterion("shflbm <=", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmLike(String value) {
            addCriterion("shflbm like", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmNotLike(String value) {
            addCriterion("shflbm not like", value, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmIn(List<String> values) {
            addCriterion("shflbm in", values, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmNotIn(List<String> values) {
            addCriterion("shflbm not in", values, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmBetween(String value1, String value2) {
            addCriterion("shflbm between", value1, value2, "shflbm");
            return (Criteria) this;
        }

        public Criteria andShflbmNotBetween(String value1, String value2) {
            addCriterion("shflbm not between", value1, value2, "shflbm");
            return (Criteria) this;
        }

        public Criteria andSlIsNull() {
            addCriterion("sl is null");
            return (Criteria) this;
        }

        public Criteria andSlIsNotNull() {
            addCriterion("sl is not null");
            return (Criteria) this;
        }

        public Criteria andSlEqualTo(String value) {
            addCriterion("sl =", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotEqualTo(String value) {
            addCriterion("sl <>", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlGreaterThan(String value) {
            addCriterion("sl >", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlGreaterThanOrEqualTo(String value) {
            addCriterion("sl >=", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlLessThan(String value) {
            addCriterion("sl <", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlLessThanOrEqualTo(String value) {
            addCriterion("sl <=", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlLike(String value) {
            addCriterion("sl like", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotLike(String value) {
            addCriterion("sl not like", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlIn(List<String> values) {
            addCriterion("sl in", values, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotIn(List<String> values) {
            addCriterion("sl not in", values, "sl");
            return (Criteria) this;
        }

        public Criteria andSlBetween(String value1, String value2) {
            addCriterion("sl between", value1, value2, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotBetween(String value1, String value2) {
            addCriterion("sl not between", value1, value2, "sl");
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