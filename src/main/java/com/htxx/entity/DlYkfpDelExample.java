package com.htxx.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DlYkfpDelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlYkfpDelExample() {
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

        public Criteria andDjhIsNull() {
            addCriterion("djh is null");
            return (Criteria) this;
        }

        public Criteria andDjhIsNotNull() {
            addCriterion("djh is not null");
            return (Criteria) this;
        }

        public Criteria andDjhEqualTo(String value) {
            addCriterion("djh =", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhNotEqualTo(String value) {
            addCriterion("djh <>", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhGreaterThan(String value) {
            addCriterion("djh >", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhGreaterThanOrEqualTo(String value) {
            addCriterion("djh >=", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhLessThan(String value) {
            addCriterion("djh <", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhLessThanOrEqualTo(String value) {
            addCriterion("djh <=", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhLike(String value) {
            addCriterion("djh like", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhNotLike(String value) {
            addCriterion("djh not like", value, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhIn(List<String> values) {
            addCriterion("djh in", values, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhNotIn(List<String> values) {
            addCriterion("djh not in", values, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhBetween(String value1, String value2) {
            addCriterion("djh between", value1, value2, "djh");
            return (Criteria) this;
        }

        public Criteria andDjhNotBetween(String value1, String value2) {
            addCriterion("djh not between", value1, value2, "djh");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(Long value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(Long value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(Long value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(Long value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(Long value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(Long value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<Long> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<Long> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(Long value1, Long value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(Long value1, Long value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXmmcIsNull() {
            addCriterion("xmmc is null");
            return (Criteria) this;
        }

        public Criteria andXmmcIsNotNull() {
            addCriterion("xmmc is not null");
            return (Criteria) this;
        }

        public Criteria andXmmcEqualTo(String value) {
            addCriterion("xmmc =", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcNotEqualTo(String value) {
            addCriterion("xmmc <>", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcGreaterThan(String value) {
            addCriterion("xmmc >", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcGreaterThanOrEqualTo(String value) {
            addCriterion("xmmc >=", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcLessThan(String value) {
            addCriterion("xmmc <", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcLessThanOrEqualTo(String value) {
            addCriterion("xmmc <=", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcLike(String value) {
            addCriterion("xmmc like", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcNotLike(String value) {
            addCriterion("xmmc not like", value, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcIn(List<String> values) {
            addCriterion("xmmc in", values, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcNotIn(List<String> values) {
            addCriterion("xmmc not in", values, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcBetween(String value1, String value2) {
            addCriterion("xmmc between", value1, value2, "xmmc");
            return (Criteria) this;
        }

        public Criteria andXmmcNotBetween(String value1, String value2) {
            addCriterion("xmmc not between", value1, value2, "xmmc");
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

        public Criteria andQyzbmIsNull() {
            addCriterion("qyzbm is null");
            return (Criteria) this;
        }

        public Criteria andQyzbmIsNotNull() {
            addCriterion("qyzbm is not null");
            return (Criteria) this;
        }

        public Criteria andQyzbmEqualTo(String value) {
            addCriterion("qyzbm =", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmNotEqualTo(String value) {
            addCriterion("qyzbm <>", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmGreaterThan(String value) {
            addCriterion("qyzbm >", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmGreaterThanOrEqualTo(String value) {
            addCriterion("qyzbm >=", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmLessThan(String value) {
            addCriterion("qyzbm <", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmLessThanOrEqualTo(String value) {
            addCriterion("qyzbm <=", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmLike(String value) {
            addCriterion("qyzbm like", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmNotLike(String value) {
            addCriterion("qyzbm not like", value, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmIn(List<String> values) {
            addCriterion("qyzbm in", values, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmNotIn(List<String> values) {
            addCriterion("qyzbm not in", values, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmBetween(String value1, String value2) {
            addCriterion("qyzbm between", value1, value2, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andQyzbmNotBetween(String value1, String value2) {
            addCriterion("qyzbm not between", value1, value2, "qyzbm");
            return (Criteria) this;
        }

        public Criteria andGgxhIsNull() {
            addCriterion("ggxh is null");
            return (Criteria) this;
        }

        public Criteria andGgxhIsNotNull() {
            addCriterion("ggxh is not null");
            return (Criteria) this;
        }

        public Criteria andGgxhEqualTo(String value) {
            addCriterion("ggxh =", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhNotEqualTo(String value) {
            addCriterion("ggxh <>", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhGreaterThan(String value) {
            addCriterion("ggxh >", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhGreaterThanOrEqualTo(String value) {
            addCriterion("ggxh >=", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhLessThan(String value) {
            addCriterion("ggxh <", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhLessThanOrEqualTo(String value) {
            addCriterion("ggxh <=", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhLike(String value) {
            addCriterion("ggxh like", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhNotLike(String value) {
            addCriterion("ggxh not like", value, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhIn(List<String> values) {
            addCriterion("ggxh in", values, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhNotIn(List<String> values) {
            addCriterion("ggxh not in", values, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhBetween(String value1, String value2) {
            addCriterion("ggxh between", value1, value2, "ggxh");
            return (Criteria) this;
        }

        public Criteria andGgxhNotBetween(String value1, String value2) {
            addCriterion("ggxh not between", value1, value2, "ggxh");
            return (Criteria) this;
        }

        public Criteria andJjdwIsNull() {
            addCriterion("jjdw is null");
            return (Criteria) this;
        }

        public Criteria andJjdwIsNotNull() {
            addCriterion("jjdw is not null");
            return (Criteria) this;
        }

        public Criteria andJjdwEqualTo(String value) {
            addCriterion("jjdw =", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotEqualTo(String value) {
            addCriterion("jjdw <>", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwGreaterThan(String value) {
            addCriterion("jjdw >", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwGreaterThanOrEqualTo(String value) {
            addCriterion("jjdw >=", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLessThan(String value) {
            addCriterion("jjdw <", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLessThanOrEqualTo(String value) {
            addCriterion("jjdw <=", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLike(String value) {
            addCriterion("jjdw like", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotLike(String value) {
            addCriterion("jjdw not like", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwIn(List<String> values) {
            addCriterion("jjdw in", values, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotIn(List<String> values) {
            addCriterion("jjdw not in", values, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwBetween(String value1, String value2) {
            addCriterion("jjdw between", value1, value2, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotBetween(String value1, String value2) {
            addCriterion("jjdw not between", value1, value2, "jjdw");
            return (Criteria) this;
        }

        public Criteria andXmslIsNull() {
            addCriterion("xmsl is null");
            return (Criteria) this;
        }

        public Criteria andXmslIsNotNull() {
            addCriterion("xmsl is not null");
            return (Criteria) this;
        }

        public Criteria andXmslEqualTo(BigDecimal value) {
            addCriterion("xmsl =", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslNotEqualTo(BigDecimal value) {
            addCriterion("xmsl <>", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslGreaterThan(BigDecimal value) {
            addCriterion("xmsl >", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("xmsl >=", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslLessThan(BigDecimal value) {
            addCriterion("xmsl <", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslLessThanOrEqualTo(BigDecimal value) {
            addCriterion("xmsl <=", value, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslIn(List<BigDecimal> values) {
            addCriterion("xmsl in", values, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslNotIn(List<BigDecimal> values) {
            addCriterion("xmsl not in", values, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmsl between", value1, value2, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmslNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmsl not between", value1, value2, "xmsl");
            return (Criteria) this;
        }

        public Criteria andXmdjIsNull() {
            addCriterion("xmdj is null");
            return (Criteria) this;
        }

        public Criteria andXmdjIsNotNull() {
            addCriterion("xmdj is not null");
            return (Criteria) this;
        }

        public Criteria andXmdjEqualTo(BigDecimal value) {
            addCriterion("xmdj =", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjNotEqualTo(BigDecimal value) {
            addCriterion("xmdj <>", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjGreaterThan(BigDecimal value) {
            addCriterion("xmdj >", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("xmdj >=", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjLessThan(BigDecimal value) {
            addCriterion("xmdj <", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjLessThanOrEqualTo(BigDecimal value) {
            addCriterion("xmdj <=", value, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjIn(List<BigDecimal> values) {
            addCriterion("xmdj in", values, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjNotIn(List<BigDecimal> values) {
            addCriterion("xmdj not in", values, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmdj between", value1, value2, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmdjNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmdj not between", value1, value2, "xmdj");
            return (Criteria) this;
        }

        public Criteria andXmjeIsNull() {
            addCriterion("xmje is null");
            return (Criteria) this;
        }

        public Criteria andXmjeIsNotNull() {
            addCriterion("xmje is not null");
            return (Criteria) this;
        }

        public Criteria andXmjeEqualTo(BigDecimal value) {
            addCriterion("xmje =", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeNotEqualTo(BigDecimal value) {
            addCriterion("xmje <>", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeGreaterThan(BigDecimal value) {
            addCriterion("xmje >", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("xmje >=", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeLessThan(BigDecimal value) {
            addCriterion("xmje <", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("xmje <=", value, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeIn(List<BigDecimal> values) {
            addCriterion("xmje in", values, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeNotIn(List<BigDecimal> values) {
            addCriterion("xmje not in", values, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmje between", value1, value2, "xmje");
            return (Criteria) this;
        }

        public Criteria andXmjeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xmje not between", value1, value2, "xmje");
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

        public Criteria andSlEqualTo(BigDecimal value) {
            addCriterion("sl =", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotEqualTo(BigDecimal value) {
            addCriterion("sl <>", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlGreaterThan(BigDecimal value) {
            addCriterion("sl >", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sl >=", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlLessThan(BigDecimal value) {
            addCriterion("sl <", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sl <=", value, "sl");
            return (Criteria) this;
        }

        public Criteria andSlIn(List<BigDecimal> values) {
            addCriterion("sl in", values, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotIn(List<BigDecimal> values) {
            addCriterion("sl not in", values, "sl");
            return (Criteria) this;
        }

        public Criteria andSlBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sl between", value1, value2, "sl");
            return (Criteria) this;
        }

        public Criteria andSlNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sl not between", value1, value2, "sl");
            return (Criteria) this;
        }

        public Criteria andSeIsNull() {
            addCriterion("se is null");
            return (Criteria) this;
        }

        public Criteria andSeIsNotNull() {
            addCriterion("se is not null");
            return (Criteria) this;
        }

        public Criteria andSeEqualTo(BigDecimal value) {
            addCriterion("se =", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotEqualTo(BigDecimal value) {
            addCriterion("se <>", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeGreaterThan(BigDecimal value) {
            addCriterion("se >", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("se >=", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeLessThan(BigDecimal value) {
            addCriterion("se <", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("se <=", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeIn(List<BigDecimal> values) {
            addCriterion("se in", values, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotIn(List<BigDecimal> values) {
            addCriterion("se not in", values, "se");
            return (Criteria) this;
        }

        public Criteria andSeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("se between", value1, value2, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("se not between", value1, value2, "se");
            return (Criteria) this;
        }

        public Criteria andHsdjIsNull() {
            addCriterion("hsdj is null");
            return (Criteria) this;
        }

        public Criteria andHsdjIsNotNull() {
            addCriterion("hsdj is not null");
            return (Criteria) this;
        }

        public Criteria andHsdjEqualTo(BigDecimal value) {
            addCriterion("hsdj =", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjNotEqualTo(BigDecimal value) {
            addCriterion("hsdj <>", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjGreaterThan(BigDecimal value) {
            addCriterion("hsdj >", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hsdj >=", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjLessThan(BigDecimal value) {
            addCriterion("hsdj <", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hsdj <=", value, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjIn(List<BigDecimal> values) {
            addCriterion("hsdj in", values, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjNotIn(List<BigDecimal> values) {
            addCriterion("hsdj not in", values, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hsdj between", value1, value2, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsdjNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hsdj not between", value1, value2, "hsdj");
            return (Criteria) this;
        }

        public Criteria andHsjeIsNull() {
            addCriterion("hsje is null");
            return (Criteria) this;
        }

        public Criteria andHsjeIsNotNull() {
            addCriterion("hsje is not null");
            return (Criteria) this;
        }

        public Criteria andHsjeEqualTo(BigDecimal value) {
            addCriterion("hsje =", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotEqualTo(BigDecimal value) {
            addCriterion("hsje <>", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeGreaterThan(BigDecimal value) {
            addCriterion("hsje >", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hsje >=", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeLessThan(BigDecimal value) {
            addCriterion("hsje <", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hsje <=", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeIn(List<BigDecimal> values) {
            addCriterion("hsje in", values, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotIn(List<BigDecimal> values) {
            addCriterion("hsje not in", values, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hsje between", value1, value2, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hsje not between", value1, value2, "hsje");
            return (Criteria) this;
        }

        public Criteria andYhzcbsIsNull() {
            addCriterion("yhzcbs is null");
            return (Criteria) this;
        }

        public Criteria andYhzcbsIsNotNull() {
            addCriterion("yhzcbs is not null");
            return (Criteria) this;
        }

        public Criteria andYhzcbsEqualTo(Byte value) {
            addCriterion("yhzcbs =", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsNotEqualTo(Byte value) {
            addCriterion("yhzcbs <>", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsGreaterThan(Byte value) {
            addCriterion("yhzcbs >", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsGreaterThanOrEqualTo(Byte value) {
            addCriterion("yhzcbs >=", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsLessThan(Byte value) {
            addCriterion("yhzcbs <", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsLessThanOrEqualTo(Byte value) {
            addCriterion("yhzcbs <=", value, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsIn(List<Byte> values) {
            addCriterion("yhzcbs in", values, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsNotIn(List<Byte> values) {
            addCriterion("yhzcbs not in", values, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsBetween(Byte value1, Byte value2) {
            addCriterion("yhzcbs between", value1, value2, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcbsNotBetween(Byte value1, Byte value2) {
            addCriterion("yhzcbs not between", value1, value2, "yhzcbs");
            return (Criteria) this;
        }

        public Criteria andYhzcnrIsNull() {
            addCriterion("yhzcnr is null");
            return (Criteria) this;
        }

        public Criteria andYhzcnrIsNotNull() {
            addCriterion("yhzcnr is not null");
            return (Criteria) this;
        }

        public Criteria andYhzcnrEqualTo(String value) {
            addCriterion("yhzcnr =", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrNotEqualTo(String value) {
            addCriterion("yhzcnr <>", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrGreaterThan(String value) {
            addCriterion("yhzcnr >", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrGreaterThanOrEqualTo(String value) {
            addCriterion("yhzcnr >=", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrLessThan(String value) {
            addCriterion("yhzcnr <", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrLessThanOrEqualTo(String value) {
            addCriterion("yhzcnr <=", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrLike(String value) {
            addCriterion("yhzcnr like", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrNotLike(String value) {
            addCriterion("yhzcnr not like", value, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrIn(List<String> values) {
            addCriterion("yhzcnr in", values, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrNotIn(List<String> values) {
            addCriterion("yhzcnr not in", values, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrBetween(String value1, String value2) {
            addCriterion("yhzcnr between", value1, value2, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andYhzcnrNotBetween(String value1, String value2) {
            addCriterion("yhzcnr not between", value1, value2, "yhzcnr");
            return (Criteria) this;
        }

        public Criteria andLslbsIsNull() {
            addCriterion("lslbs is null");
            return (Criteria) this;
        }

        public Criteria andLslbsIsNotNull() {
            addCriterion("lslbs is not null");
            return (Criteria) this;
        }

        public Criteria andLslbsEqualTo(Byte value) {
            addCriterion("lslbs =", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsNotEqualTo(Byte value) {
            addCriterion("lslbs <>", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsGreaterThan(Byte value) {
            addCriterion("lslbs >", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsGreaterThanOrEqualTo(Byte value) {
            addCriterion("lslbs >=", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsLessThan(Byte value) {
            addCriterion("lslbs <", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsLessThanOrEqualTo(Byte value) {
            addCriterion("lslbs <=", value, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsIn(List<Byte> values) {
            addCriterion("lslbs in", values, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsNotIn(List<Byte> values) {
            addCriterion("lslbs not in", values, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsBetween(Byte value1, Byte value2) {
            addCriterion("lslbs between", value1, value2, "lslbs");
            return (Criteria) this;
        }

        public Criteria andLslbsNotBetween(Byte value1, Byte value2) {
            addCriterion("lslbs not between", value1, value2, "lslbs");
            return (Criteria) this;
        }

        public Criteria andFphxzIsNull() {
            addCriterion("fphxz is null");
            return (Criteria) this;
        }

        public Criteria andFphxzIsNotNull() {
            addCriterion("fphxz is not null");
            return (Criteria) this;
        }

        public Criteria andFphxzEqualTo(Byte value) {
            addCriterion("fphxz =", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzNotEqualTo(Byte value) {
            addCriterion("fphxz <>", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzGreaterThan(Byte value) {
            addCriterion("fphxz >", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzGreaterThanOrEqualTo(Byte value) {
            addCriterion("fphxz >=", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzLessThan(Byte value) {
            addCriterion("fphxz <", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzLessThanOrEqualTo(Byte value) {
            addCriterion("fphxz <=", value, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzIn(List<Byte> values) {
            addCriterion("fphxz in", values, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzNotIn(List<Byte> values) {
            addCriterion("fphxz not in", values, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzBetween(Byte value1, Byte value2) {
            addCriterion("fphxz between", value1, value2, "fphxz");
            return (Criteria) this;
        }

        public Criteria andFphxzNotBetween(Byte value1, Byte value2) {
            addCriterion("fphxz not between", value1, value2, "fphxz");
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

        public Criteria andHsbzIsNull() {
            addCriterion("hsbz is null");
            return (Criteria) this;
        }

        public Criteria andHsbzIsNotNull() {
            addCriterion("hsbz is not null");
            return (Criteria) this;
        }

        public Criteria andHsbzEqualTo(Byte value) {
            addCriterion("hsbz =", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzNotEqualTo(Byte value) {
            addCriterion("hsbz <>", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzGreaterThan(Byte value) {
            addCriterion("hsbz >", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzGreaterThanOrEqualTo(Byte value) {
            addCriterion("hsbz >=", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzLessThan(Byte value) {
            addCriterion("hsbz <", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzLessThanOrEqualTo(Byte value) {
            addCriterion("hsbz <=", value, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzIn(List<Byte> values) {
            addCriterion("hsbz in", values, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzNotIn(List<Byte> values) {
            addCriterion("hsbz not in", values, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzBetween(Byte value1, Byte value2) {
            addCriterion("hsbz between", value1, value2, "hsbz");
            return (Criteria) this;
        }

        public Criteria andHsbzNotBetween(Byte value1, Byte value2) {
            addCriterion("hsbz not between", value1, value2, "hsbz");
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