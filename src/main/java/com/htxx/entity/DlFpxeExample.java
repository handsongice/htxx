package com.htxx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DlFpxeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlFpxeExample() {
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

        public Criteria andKpjIdIsNull() {
            addCriterion("kpj_id is null");
            return (Criteria) this;
        }

        public Criteria andKpjIdIsNotNull() {
            addCriterion("kpj_id is not null");
            return (Criteria) this;
        }

        public Criteria andKpjIdEqualTo(Long value) {
            addCriterion("kpj_id =", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdNotEqualTo(Long value) {
            addCriterion("kpj_id <>", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdGreaterThan(Long value) {
            addCriterion("kpj_id >", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("kpj_id >=", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdLessThan(Long value) {
            addCriterion("kpj_id <", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdLessThanOrEqualTo(Long value) {
            addCriterion("kpj_id <=", value, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdIn(List<Long> values) {
            addCriterion("kpj_id in", values, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdNotIn(List<Long> values) {
            addCriterion("kpj_id not in", values, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdBetween(Long value1, Long value2) {
            addCriterion("kpj_id between", value1, value2, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIdNotBetween(Long value1, Long value2) {
            addCriterion("kpj_id not between", value1, value2, "kpjId");
            return (Criteria) this;
        }

        public Criteria andKpjIsNull() {
            addCriterion("kpj is null");
            return (Criteria) this;
        }

        public Criteria andKpjIsNotNull() {
            addCriterion("kpj is not null");
            return (Criteria) this;
        }

        public Criteria andKpjEqualTo(String value) {
            addCriterion("kpj =", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjNotEqualTo(String value) {
            addCriterion("kpj <>", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjGreaterThan(String value) {
            addCriterion("kpj >", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjGreaterThanOrEqualTo(String value) {
            addCriterion("kpj >=", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjLessThan(String value) {
            addCriterion("kpj <", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjLessThanOrEqualTo(String value) {
            addCriterion("kpj <=", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjLike(String value) {
            addCriterion("kpj like", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjNotLike(String value) {
            addCriterion("kpj not like", value, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjIn(List<String> values) {
            addCriterion("kpj in", values, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjNotIn(List<String> values) {
            addCriterion("kpj not in", values, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjBetween(String value1, String value2) {
            addCriterion("kpj between", value1, value2, "kpj");
            return (Criteria) this;
        }

        public Criteria andKpjNotBetween(String value1, String value2) {
            addCriterion("kpj not between", value1, value2, "kpj");
            return (Criteria) this;
        }

        public Criteria andFplxIdIsNull() {
            addCriterion("fplx_id is null");
            return (Criteria) this;
        }

        public Criteria andFplxIdIsNotNull() {
            addCriterion("fplx_id is not null");
            return (Criteria) this;
        }

        public Criteria andFplxIdEqualTo(Long value) {
            addCriterion("fplx_id =", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdNotEqualTo(Long value) {
            addCriterion("fplx_id <>", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdGreaterThan(Long value) {
            addCriterion("fplx_id >", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fplx_id >=", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdLessThan(Long value) {
            addCriterion("fplx_id <", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdLessThanOrEqualTo(Long value) {
            addCriterion("fplx_id <=", value, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdIn(List<Long> values) {
            addCriterion("fplx_id in", values, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdNotIn(List<Long> values) {
            addCriterion("fplx_id not in", values, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdBetween(Long value1, Long value2) {
            addCriterion("fplx_id between", value1, value2, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIdNotBetween(Long value1, Long value2) {
            addCriterion("fplx_id not between", value1, value2, "fplxId");
            return (Criteria) this;
        }

        public Criteria andFplxIsNull() {
            addCriterion("fplx is null");
            return (Criteria) this;
        }

        public Criteria andFplxIsNotNull() {
            addCriterion("fplx is not null");
            return (Criteria) this;
        }

        public Criteria andFplxEqualTo(String value) {
            addCriterion("fplx =", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxNotEqualTo(String value) {
            addCriterion("fplx <>", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxGreaterThan(String value) {
            addCriterion("fplx >", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxGreaterThanOrEqualTo(String value) {
            addCriterion("fplx >=", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxLessThan(String value) {
            addCriterion("fplx <", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxLessThanOrEqualTo(String value) {
            addCriterion("fplx <=", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxLike(String value) {
            addCriterion("fplx like", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxNotLike(String value) {
            addCriterion("fplx not like", value, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxIn(List<String> values) {
            addCriterion("fplx in", values, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxNotIn(List<String> values) {
            addCriterion("fplx not in", values, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxBetween(String value1, String value2) {
            addCriterion("fplx between", value1, value2, "fplx");
            return (Criteria) this;
        }

        public Criteria andFplxNotBetween(String value1, String value2) {
            addCriterion("fplx not between", value1, value2, "fplx");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
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