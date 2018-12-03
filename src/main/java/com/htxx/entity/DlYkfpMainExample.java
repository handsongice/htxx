package com.htxx.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DlYkfpMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlYkfpMainExample() {
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

        public Criteria andFjhIsNull() {
            addCriterion("fjh is null");
            return (Criteria) this;
        }

        public Criteria andFjhIsNotNull() {
            addCriterion("fjh is not null");
            return (Criteria) this;
        }

        public Criteria andFjhEqualTo(Byte value) {
            addCriterion("fjh =", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhNotEqualTo(Byte value) {
            addCriterion("fjh <>", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhGreaterThan(Byte value) {
            addCriterion("fjh >", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhGreaterThanOrEqualTo(Byte value) {
            addCriterion("fjh >=", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhLessThan(Byte value) {
            addCriterion("fjh <", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhLessThanOrEqualTo(Byte value) {
            addCriterion("fjh <=", value, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhIn(List<Byte> values) {
            addCriterion("fjh in", values, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhNotIn(List<Byte> values) {
            addCriterion("fjh not in", values, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhBetween(Byte value1, Byte value2) {
            addCriterion("fjh between", value1, value2, "fjh");
            return (Criteria) this;
        }

        public Criteria andFjhNotBetween(Byte value1, Byte value2) {
            addCriterion("fjh not between", value1, value2, "fjh");
            return (Criteria) this;
        }

        public Criteria andKpzdhIsNull() {
            addCriterion("kpzdh is null");
            return (Criteria) this;
        }

        public Criteria andKpzdhIsNotNull() {
            addCriterion("kpzdh is not null");
            return (Criteria) this;
        }

        public Criteria andKpzdhEqualTo(String value) {
            addCriterion("kpzdh =", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhNotEqualTo(String value) {
            addCriterion("kpzdh <>", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhGreaterThan(String value) {
            addCriterion("kpzdh >", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhGreaterThanOrEqualTo(String value) {
            addCriterion("kpzdh >=", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhLessThan(String value) {
            addCriterion("kpzdh <", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhLessThanOrEqualTo(String value) {
            addCriterion("kpzdh <=", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhLike(String value) {
            addCriterion("kpzdh like", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhNotLike(String value) {
            addCriterion("kpzdh not like", value, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhIn(List<String> values) {
            addCriterion("kpzdh in", values, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhNotIn(List<String> values) {
            addCriterion("kpzdh not in", values, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhBetween(String value1, String value2) {
            addCriterion("kpzdh between", value1, value2, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andKpzdhNotBetween(String value1, String value2) {
            addCriterion("kpzdh not between", value1, value2, "kpzdh");
            return (Criteria) this;
        }

        public Criteria andFpzlIsNull() {
            addCriterion("fpzl is null");
            return (Criteria) this;
        }

        public Criteria andFpzlIsNotNull() {
            addCriterion("fpzl is not null");
            return (Criteria) this;
        }

        public Criteria andFpzlEqualTo(Byte value) {
            addCriterion("fpzl =", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlNotEqualTo(Byte value) {
            addCriterion("fpzl <>", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlGreaterThan(Byte value) {
            addCriterion("fpzl >", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlGreaterThanOrEqualTo(Byte value) {
            addCriterion("fpzl >=", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlLessThan(Byte value) {
            addCriterion("fpzl <", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlLessThanOrEqualTo(Byte value) {
            addCriterion("fpzl <=", value, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlIn(List<Byte> values) {
            addCriterion("fpzl in", values, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlNotIn(List<Byte> values) {
            addCriterion("fpzl not in", values, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlBetween(Byte value1, Byte value2) {
            addCriterion("fpzl between", value1, value2, "fpzl");
            return (Criteria) this;
        }

        public Criteria andFpzlNotBetween(Byte value1, Byte value2) {
            addCriterion("fpzl not between", value1, value2, "fpzl");
            return (Criteria) this;
        }

        public Criteria andKplxIsNull() {
            addCriterion("kplx is null");
            return (Criteria) this;
        }

        public Criteria andKplxIsNotNull() {
            addCriterion("kplx is not null");
            return (Criteria) this;
        }

        public Criteria andKplxEqualTo(Byte value) {
            addCriterion("kplx =", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxNotEqualTo(Byte value) {
            addCriterion("kplx <>", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxGreaterThan(Byte value) {
            addCriterion("kplx >", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxGreaterThanOrEqualTo(Byte value) {
            addCriterion("kplx >=", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxLessThan(Byte value) {
            addCriterion("kplx <", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxLessThanOrEqualTo(Byte value) {
            addCriterion("kplx <=", value, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxIn(List<Byte> values) {
            addCriterion("kplx in", values, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxNotIn(List<Byte> values) {
            addCriterion("kplx not in", values, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxBetween(Byte value1, Byte value2) {
            addCriterion("kplx between", value1, value2, "kplx");
            return (Criteria) this;
        }

        public Criteria andKplxNotBetween(Byte value1, Byte value2) {
            addCriterion("kplx not between", value1, value2, "kplx");
            return (Criteria) this;
        }

        public Criteria andXfshIsNull() {
            addCriterion("xfsh is null");
            return (Criteria) this;
        }

        public Criteria andXfshIsNotNull() {
            addCriterion("xfsh is not null");
            return (Criteria) this;
        }

        public Criteria andXfshEqualTo(String value) {
            addCriterion("xfsh =", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshNotEqualTo(String value) {
            addCriterion("xfsh <>", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshGreaterThan(String value) {
            addCriterion("xfsh >", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshGreaterThanOrEqualTo(String value) {
            addCriterion("xfsh >=", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshLessThan(String value) {
            addCriterion("xfsh <", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshLessThanOrEqualTo(String value) {
            addCriterion("xfsh <=", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshLike(String value) {
            addCriterion("xfsh like", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshNotLike(String value) {
            addCriterion("xfsh not like", value, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshIn(List<String> values) {
            addCriterion("xfsh in", values, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshNotIn(List<String> values) {
            addCriterion("xfsh not in", values, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshBetween(String value1, String value2) {
            addCriterion("xfsh between", value1, value2, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfshNotBetween(String value1, String value2) {
            addCriterion("xfsh not between", value1, value2, "xfsh");
            return (Criteria) this;
        }

        public Criteria andXfmcIsNull() {
            addCriterion("xfmc is null");
            return (Criteria) this;
        }

        public Criteria andXfmcIsNotNull() {
            addCriterion("xfmc is not null");
            return (Criteria) this;
        }

        public Criteria andXfmcEqualTo(String value) {
            addCriterion("xfmc =", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcNotEqualTo(String value) {
            addCriterion("xfmc <>", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcGreaterThan(String value) {
            addCriterion("xfmc >", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcGreaterThanOrEqualTo(String value) {
            addCriterion("xfmc >=", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcLessThan(String value) {
            addCriterion("xfmc <", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcLessThanOrEqualTo(String value) {
            addCriterion("xfmc <=", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcLike(String value) {
            addCriterion("xfmc like", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcNotLike(String value) {
            addCriterion("xfmc not like", value, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcIn(List<String> values) {
            addCriterion("xfmc in", values, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcNotIn(List<String> values) {
            addCriterion("xfmc not in", values, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcBetween(String value1, String value2) {
            addCriterion("xfmc between", value1, value2, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfmcNotBetween(String value1, String value2) {
            addCriterion("xfmc not between", value1, value2, "xfmc");
            return (Criteria) this;
        }

        public Criteria andXfdzdhIsNull() {
            addCriterion("xfdzdh is null");
            return (Criteria) this;
        }

        public Criteria andXfdzdhIsNotNull() {
            addCriterion("xfdzdh is not null");
            return (Criteria) this;
        }

        public Criteria andXfdzdhEqualTo(String value) {
            addCriterion("xfdzdh =", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhNotEqualTo(String value) {
            addCriterion("xfdzdh <>", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhGreaterThan(String value) {
            addCriterion("xfdzdh >", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhGreaterThanOrEqualTo(String value) {
            addCriterion("xfdzdh >=", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhLessThan(String value) {
            addCriterion("xfdzdh <", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhLessThanOrEqualTo(String value) {
            addCriterion("xfdzdh <=", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhLike(String value) {
            addCriterion("xfdzdh like", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhNotLike(String value) {
            addCriterion("xfdzdh not like", value, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhIn(List<String> values) {
            addCriterion("xfdzdh in", values, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhNotIn(List<String> values) {
            addCriterion("xfdzdh not in", values, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhBetween(String value1, String value2) {
            addCriterion("xfdzdh between", value1, value2, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfdzdhNotBetween(String value1, String value2) {
            addCriterion("xfdzdh not between", value1, value2, "xfdzdh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhIsNull() {
            addCriterion("xfyhzh is null");
            return (Criteria) this;
        }

        public Criteria andXfyhzhIsNotNull() {
            addCriterion("xfyhzh is not null");
            return (Criteria) this;
        }

        public Criteria andXfyhzhEqualTo(String value) {
            addCriterion("xfyhzh =", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhNotEqualTo(String value) {
            addCriterion("xfyhzh <>", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhGreaterThan(String value) {
            addCriterion("xfyhzh >", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhGreaterThanOrEqualTo(String value) {
            addCriterion("xfyhzh >=", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhLessThan(String value) {
            addCriterion("xfyhzh <", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhLessThanOrEqualTo(String value) {
            addCriterion("xfyhzh <=", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhLike(String value) {
            addCriterion("xfyhzh like", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhNotLike(String value) {
            addCriterion("xfyhzh not like", value, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhIn(List<String> values) {
            addCriterion("xfyhzh in", values, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhNotIn(List<String> values) {
            addCriterion("xfyhzh not in", values, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhBetween(String value1, String value2) {
            addCriterion("xfyhzh between", value1, value2, "xfyhzh");
            return (Criteria) this;
        }

        public Criteria andXfyhzhNotBetween(String value1, String value2) {
            addCriterion("xfyhzh not between", value1, value2, "xfyhzh");
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

        public Criteria andGfdzdhIsNull() {
            addCriterion("gfdzdh is null");
            return (Criteria) this;
        }

        public Criteria andGfdzdhIsNotNull() {
            addCriterion("gfdzdh is not null");
            return (Criteria) this;
        }

        public Criteria andGfdzdhEqualTo(String value) {
            addCriterion("gfdzdh =", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhNotEqualTo(String value) {
            addCriterion("gfdzdh <>", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhGreaterThan(String value) {
            addCriterion("gfdzdh >", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhGreaterThanOrEqualTo(String value) {
            addCriterion("gfdzdh >=", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhLessThan(String value) {
            addCriterion("gfdzdh <", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhLessThanOrEqualTo(String value) {
            addCriterion("gfdzdh <=", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhLike(String value) {
            addCriterion("gfdzdh like", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhNotLike(String value) {
            addCriterion("gfdzdh not like", value, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhIn(List<String> values) {
            addCriterion("gfdzdh in", values, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhNotIn(List<String> values) {
            addCriterion("gfdzdh not in", values, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhBetween(String value1, String value2) {
            addCriterion("gfdzdh between", value1, value2, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfdzdhNotBetween(String value1, String value2) {
            addCriterion("gfdzdh not between", value1, value2, "gfdzdh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhIsNull() {
            addCriterion("gfyhzh is null");
            return (Criteria) this;
        }

        public Criteria andGfyhzhIsNotNull() {
            addCriterion("gfyhzh is not null");
            return (Criteria) this;
        }

        public Criteria andGfyhzhEqualTo(String value) {
            addCriterion("gfyhzh =", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhNotEqualTo(String value) {
            addCriterion("gfyhzh <>", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhGreaterThan(String value) {
            addCriterion("gfyhzh >", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhGreaterThanOrEqualTo(String value) {
            addCriterion("gfyhzh >=", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhLessThan(String value) {
            addCriterion("gfyhzh <", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhLessThanOrEqualTo(String value) {
            addCriterion("gfyhzh <=", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhLike(String value) {
            addCriterion("gfyhzh like", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhNotLike(String value) {
            addCriterion("gfyhzh not like", value, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhIn(List<String> values) {
            addCriterion("gfyhzh in", values, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhNotIn(List<String> values) {
            addCriterion("gfyhzh not in", values, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhBetween(String value1, String value2) {
            addCriterion("gfyhzh between", value1, value2, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andGfyhzhNotBetween(String value1, String value2) {
            addCriterion("gfyhzh not between", value1, value2, "gfyhzh");
            return (Criteria) this;
        }

        public Criteria andKprIsNull() {
            addCriterion("kpr is null");
            return (Criteria) this;
        }

        public Criteria andKprIsNotNull() {
            addCriterion("kpr is not null");
            return (Criteria) this;
        }

        public Criteria andKprEqualTo(String value) {
            addCriterion("kpr =", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprNotEqualTo(String value) {
            addCriterion("kpr <>", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprGreaterThan(String value) {
            addCriterion("kpr >", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprGreaterThanOrEqualTo(String value) {
            addCriterion("kpr >=", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprLessThan(String value) {
            addCriterion("kpr <", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprLessThanOrEqualTo(String value) {
            addCriterion("kpr <=", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprLike(String value) {
            addCriterion("kpr like", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprNotLike(String value) {
            addCriterion("kpr not like", value, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprIn(List<String> values) {
            addCriterion("kpr in", values, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprNotIn(List<String> values) {
            addCriterion("kpr not in", values, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprBetween(String value1, String value2) {
            addCriterion("kpr between", value1, value2, "kpr");
            return (Criteria) this;
        }

        public Criteria andKprNotBetween(String value1, String value2) {
            addCriterion("kpr not between", value1, value2, "kpr");
            return (Criteria) this;
        }

        public Criteria andSkrIsNull() {
            addCriterion("skr is null");
            return (Criteria) this;
        }

        public Criteria andSkrIsNotNull() {
            addCriterion("skr is not null");
            return (Criteria) this;
        }

        public Criteria andSkrEqualTo(String value) {
            addCriterion("skr =", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrNotEqualTo(String value) {
            addCriterion("skr <>", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrGreaterThan(String value) {
            addCriterion("skr >", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrGreaterThanOrEqualTo(String value) {
            addCriterion("skr >=", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrLessThan(String value) {
            addCriterion("skr <", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrLessThanOrEqualTo(String value) {
            addCriterion("skr <=", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrLike(String value) {
            addCriterion("skr like", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrNotLike(String value) {
            addCriterion("skr not like", value, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrIn(List<String> values) {
            addCriterion("skr in", values, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrNotIn(List<String> values) {
            addCriterion("skr not in", values, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrBetween(String value1, String value2) {
            addCriterion("skr between", value1, value2, "skr");
            return (Criteria) this;
        }

        public Criteria andSkrNotBetween(String value1, String value2) {
            addCriterion("skr not between", value1, value2, "skr");
            return (Criteria) this;
        }

        public Criteria andFhrIsNull() {
            addCriterion("fhr is null");
            return (Criteria) this;
        }

        public Criteria andFhrIsNotNull() {
            addCriterion("fhr is not null");
            return (Criteria) this;
        }

        public Criteria andFhrEqualTo(String value) {
            addCriterion("fhr =", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrNotEqualTo(String value) {
            addCriterion("fhr <>", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrGreaterThan(String value) {
            addCriterion("fhr >", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrGreaterThanOrEqualTo(String value) {
            addCriterion("fhr >=", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrLessThan(String value) {
            addCriterion("fhr <", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrLessThanOrEqualTo(String value) {
            addCriterion("fhr <=", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrLike(String value) {
            addCriterion("fhr like", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrNotLike(String value) {
            addCriterion("fhr not like", value, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrIn(List<String> values) {
            addCriterion("fhr in", values, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrNotIn(List<String> values) {
            addCriterion("fhr not in", values, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrBetween(String value1, String value2) {
            addCriterion("fhr between", value1, value2, "fhr");
            return (Criteria) this;
        }

        public Criteria andFhrNotBetween(String value1, String value2) {
            addCriterion("fhr not between", value1, value2, "fhr");
            return (Criteria) this;
        }

        public Criteria andYfpdmIsNull() {
            addCriterion("yfpdm is null");
            return (Criteria) this;
        }

        public Criteria andYfpdmIsNotNull() {
            addCriterion("yfpdm is not null");
            return (Criteria) this;
        }

        public Criteria andYfpdmEqualTo(String value) {
            addCriterion("yfpdm =", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmNotEqualTo(String value) {
            addCriterion("yfpdm <>", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmGreaterThan(String value) {
            addCriterion("yfpdm >", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmGreaterThanOrEqualTo(String value) {
            addCriterion("yfpdm >=", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmLessThan(String value) {
            addCriterion("yfpdm <", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmLessThanOrEqualTo(String value) {
            addCriterion("yfpdm <=", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmLike(String value) {
            addCriterion("yfpdm like", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmNotLike(String value) {
            addCriterion("yfpdm not like", value, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmIn(List<String> values) {
            addCriterion("yfpdm in", values, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmNotIn(List<String> values) {
            addCriterion("yfpdm not in", values, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmBetween(String value1, String value2) {
            addCriterion("yfpdm between", value1, value2, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfpdmNotBetween(String value1, String value2) {
            addCriterion("yfpdm not between", value1, value2, "yfpdm");
            return (Criteria) this;
        }

        public Criteria andYfphmIsNull() {
            addCriterion("yfphm is null");
            return (Criteria) this;
        }

        public Criteria andYfphmIsNotNull() {
            addCriterion("yfphm is not null");
            return (Criteria) this;
        }

        public Criteria andYfphmEqualTo(String value) {
            addCriterion("yfphm =", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmNotEqualTo(String value) {
            addCriterion("yfphm <>", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmGreaterThan(String value) {
            addCriterion("yfphm >", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmGreaterThanOrEqualTo(String value) {
            addCriterion("yfphm >=", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmLessThan(String value) {
            addCriterion("yfphm <", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmLessThanOrEqualTo(String value) {
            addCriterion("yfphm <=", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmLike(String value) {
            addCriterion("yfphm like", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmNotLike(String value) {
            addCriterion("yfphm not like", value, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmIn(List<String> values) {
            addCriterion("yfphm in", values, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmNotIn(List<String> values) {
            addCriterion("yfphm not in", values, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmBetween(String value1, String value2) {
            addCriterion("yfphm between", value1, value2, "yfphm");
            return (Criteria) this;
        }

        public Criteria andYfphmNotBetween(String value1, String value2) {
            addCriterion("yfphm not between", value1, value2, "yfphm");
            return (Criteria) this;
        }

        public Criteria andQdbzIsNull() {
            addCriterion("qdbz is null");
            return (Criteria) this;
        }

        public Criteria andQdbzIsNotNull() {
            addCriterion("qdbz is not null");
            return (Criteria) this;
        }

        public Criteria andQdbzEqualTo(Byte value) {
            addCriterion("qdbz =", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzNotEqualTo(Byte value) {
            addCriterion("qdbz <>", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzGreaterThan(Byte value) {
            addCriterion("qdbz >", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzGreaterThanOrEqualTo(Byte value) {
            addCriterion("qdbz >=", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzLessThan(Byte value) {
            addCriterion("qdbz <", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzLessThanOrEqualTo(Byte value) {
            addCriterion("qdbz <=", value, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzIn(List<Byte> values) {
            addCriterion("qdbz in", values, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzNotIn(List<Byte> values) {
            addCriterion("qdbz not in", values, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzBetween(Byte value1, Byte value2) {
            addCriterion("qdbz between", value1, value2, "qdbz");
            return (Criteria) this;
        }

        public Criteria andQdbzNotBetween(Byte value1, Byte value2) {
            addCriterion("qdbz not between", value1, value2, "qdbz");
            return (Criteria) this;
        }

        public Criteria andJshjIsNull() {
            addCriterion("jshj is null");
            return (Criteria) this;
        }

        public Criteria andJshjIsNotNull() {
            addCriterion("jshj is not null");
            return (Criteria) this;
        }

        public Criteria andJshjEqualTo(BigDecimal value) {
            addCriterion("jshj =", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjNotEqualTo(BigDecimal value) {
            addCriterion("jshj <>", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjGreaterThan(BigDecimal value) {
            addCriterion("jshj >", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("jshj >=", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjLessThan(BigDecimal value) {
            addCriterion("jshj <", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjLessThanOrEqualTo(BigDecimal value) {
            addCriterion("jshj <=", value, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjIn(List<BigDecimal> values) {
            addCriterion("jshj in", values, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjNotIn(List<BigDecimal> values) {
            addCriterion("jshj not in", values, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("jshj between", value1, value2, "jshj");
            return (Criteria) this;
        }

        public Criteria andJshjNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("jshj not between", value1, value2, "jshj");
            return (Criteria) this;
        }

        public Criteria andHjjeIsNull() {
            addCriterion("hjje is null");
            return (Criteria) this;
        }

        public Criteria andHjjeIsNotNull() {
            addCriterion("hjje is not null");
            return (Criteria) this;
        }

        public Criteria andHjjeEqualTo(BigDecimal value) {
            addCriterion("hjje =", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeNotEqualTo(BigDecimal value) {
            addCriterion("hjje <>", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeGreaterThan(BigDecimal value) {
            addCriterion("hjje >", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hjje >=", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeLessThan(BigDecimal value) {
            addCriterion("hjje <", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hjje <=", value, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeIn(List<BigDecimal> values) {
            addCriterion("hjje in", values, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeNotIn(List<BigDecimal> values) {
            addCriterion("hjje not in", values, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hjje between", value1, value2, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjjeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hjje not between", value1, value2, "hjje");
            return (Criteria) this;
        }

        public Criteria andHjseIsNull() {
            addCriterion("hjse is null");
            return (Criteria) this;
        }

        public Criteria andHjseIsNotNull() {
            addCriterion("hjse is not null");
            return (Criteria) this;
        }

        public Criteria andHjseEqualTo(BigDecimal value) {
            addCriterion("hjse =", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseNotEqualTo(BigDecimal value) {
            addCriterion("hjse <>", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseGreaterThan(BigDecimal value) {
            addCriterion("hjse >", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hjse >=", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseLessThan(BigDecimal value) {
            addCriterion("hjse <", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hjse <=", value, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseIn(List<BigDecimal> values) {
            addCriterion("hjse in", values, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseNotIn(List<BigDecimal> values) {
            addCriterion("hjse not in", values, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hjse between", value1, value2, "hjse");
            return (Criteria) this;
        }

        public Criteria andHjseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hjse not between", value1, value2, "hjse");
            return (Criteria) this;
        }

        public Criteria andBmbbhIsNull() {
            addCriterion("bmbbh is null");
            return (Criteria) this;
        }

        public Criteria andBmbbhIsNotNull() {
            addCriterion("bmbbh is not null");
            return (Criteria) this;
        }

        public Criteria andBmbbhEqualTo(String value) {
            addCriterion("bmbbh =", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhNotEqualTo(String value) {
            addCriterion("bmbbh <>", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhGreaterThan(String value) {
            addCriterion("bmbbh >", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhGreaterThanOrEqualTo(String value) {
            addCriterion("bmbbh >=", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhLessThan(String value) {
            addCriterion("bmbbh <", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhLessThanOrEqualTo(String value) {
            addCriterion("bmbbh <=", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhLike(String value) {
            addCriterion("bmbbh like", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhNotLike(String value) {
            addCriterion("bmbbh not like", value, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhIn(List<String> values) {
            addCriterion("bmbbh in", values, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhNotIn(List<String> values) {
            addCriterion("bmbbh not in", values, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhBetween(String value1, String value2) {
            addCriterion("bmbbh between", value1, value2, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBmbbhNotBetween(String value1, String value2) {
            addCriterion("bmbbh not between", value1, value2, "bmbbh");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("bz is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("bz is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("bz =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("bz <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("bz >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("bz >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("bz <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("bz <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("bz like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("bz not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("bz in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("bz not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("bz between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("bz not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andSjIsNull() {
            addCriterion("sj is null");
            return (Criteria) this;
        }

        public Criteria andSjIsNotNull() {
            addCriterion("sj is not null");
            return (Criteria) this;
        }

        public Criteria andSjEqualTo(String value) {
            addCriterion("sj =", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotEqualTo(String value) {
            addCriterion("sj <>", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjGreaterThan(String value) {
            addCriterion("sj >", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjGreaterThanOrEqualTo(String value) {
            addCriterion("sj >=", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLessThan(String value) {
            addCriterion("sj <", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLessThanOrEqualTo(String value) {
            addCriterion("sj <=", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLike(String value) {
            addCriterion("sj like", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotLike(String value) {
            addCriterion("sj not like", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjIn(List<String> values) {
            addCriterion("sj in", values, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotIn(List<String> values) {
            addCriterion("sj not in", values, "sj");
            return (Criteria) this;
        }

        public Criteria andSjBetween(String value1, String value2) {
            addCriterion("sj between", value1, value2, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotBetween(String value1, String value2) {
            addCriterion("sj not between", value1, value2, "sj");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andKpsjIsNull() {
            addCriterion("kpsj is null");
            return (Criteria) this;
        }

        public Criteria andKpsjIsNotNull() {
            addCriterion("kpsj is not null");
            return (Criteria) this;
        }

        public Criteria andKpsjEqualTo(Date value) {
            addCriterion("kpsj =", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotEqualTo(Date value) {
            addCriterion("kpsj <>", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjGreaterThan(Date value) {
            addCriterion("kpsj >", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjGreaterThanOrEqualTo(Date value) {
            addCriterion("kpsj >=", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjLessThan(Date value) {
            addCriterion("kpsj <", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjLessThanOrEqualTo(Date value) {
            addCriterion("kpsj <=", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjIn(List<Date> values) {
            addCriterion("kpsj in", values, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotIn(List<Date> values) {
            addCriterion("kpsj not in", values, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjBetween(Date value1, Date value2) {
            addCriterion("kpsj between", value1, value2, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotBetween(Date value1, Date value2) {
            addCriterion("kpsj not between", value1, value2, "kpsj");
            return (Criteria) this;
        }

        public Criteria andFpdmIsNull() {
            addCriterion("fpdm is null");
            return (Criteria) this;
        }

        public Criteria andFpdmIsNotNull() {
            addCriterion("fpdm is not null");
            return (Criteria) this;
        }

        public Criteria andFpdmEqualTo(String value) {
            addCriterion("fpdm =", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmNotEqualTo(String value) {
            addCriterion("fpdm <>", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmGreaterThan(String value) {
            addCriterion("fpdm >", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmGreaterThanOrEqualTo(String value) {
            addCriterion("fpdm >=", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmLessThan(String value) {
            addCriterion("fpdm <", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmLessThanOrEqualTo(String value) {
            addCriterion("fpdm <=", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmLike(String value) {
            addCriterion("fpdm like", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmNotLike(String value) {
            addCriterion("fpdm not like", value, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmIn(List<String> values) {
            addCriterion("fpdm in", values, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmNotIn(List<String> values) {
            addCriterion("fpdm not in", values, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmBetween(String value1, String value2) {
            addCriterion("fpdm between", value1, value2, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFpdmNotBetween(String value1, String value2) {
            addCriterion("fpdm not between", value1, value2, "fpdm");
            return (Criteria) this;
        }

        public Criteria andFphmIsNull() {
            addCriterion("fphm is null");
            return (Criteria) this;
        }

        public Criteria andFphmIsNotNull() {
            addCriterion("fphm is not null");
            return (Criteria) this;
        }

        public Criteria andFphmEqualTo(String value) {
            addCriterion("fphm =", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmNotEqualTo(String value) {
            addCriterion("fphm <>", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmGreaterThan(String value) {
            addCriterion("fphm >", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmGreaterThanOrEqualTo(String value) {
            addCriterion("fphm >=", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmLessThan(String value) {
            addCriterion("fphm <", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmLessThanOrEqualTo(String value) {
            addCriterion("fphm <=", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmLike(String value) {
            addCriterion("fphm like", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmNotLike(String value) {
            addCriterion("fphm not like", value, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmIn(List<String> values) {
            addCriterion("fphm in", values, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmNotIn(List<String> values) {
            addCriterion("fphm not in", values, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmBetween(String value1, String value2) {
            addCriterion("fphm between", value1, value2, "fphm");
            return (Criteria) this;
        }

        public Criteria andFphmNotBetween(String value1, String value2) {
            addCriterion("fphm not between", value1, value2, "fphm");
            return (Criteria) this;
        }

        public Criteria andChbzIsNull() {
            addCriterion("chbz is null");
            return (Criteria) this;
        }

        public Criteria andChbzIsNotNull() {
            addCriterion("chbz is not null");
            return (Criteria) this;
        }

        public Criteria andChbzEqualTo(Byte value) {
            addCriterion("chbz =", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzNotEqualTo(Byte value) {
            addCriterion("chbz <>", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzGreaterThan(Byte value) {
            addCriterion("chbz >", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzGreaterThanOrEqualTo(Byte value) {
            addCriterion("chbz >=", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzLessThan(Byte value) {
            addCriterion("chbz <", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzLessThanOrEqualTo(Byte value) {
            addCriterion("chbz <=", value, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzIn(List<Byte> values) {
            addCriterion("chbz in", values, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzNotIn(List<Byte> values) {
            addCriterion("chbz not in", values, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzBetween(Byte value1, Byte value2) {
            addCriterion("chbz between", value1, value2, "chbz");
            return (Criteria) this;
        }

        public Criteria andChbzNotBetween(Byte value1, Byte value2) {
            addCriterion("chbz not between", value1, value2, "chbz");
            return (Criteria) this;
        }

        public Criteria andZfbzIsNull() {
            addCriterion("zfbz is null");
            return (Criteria) this;
        }

        public Criteria andZfbzIsNotNull() {
            addCriterion("zfbz is not null");
            return (Criteria) this;
        }

        public Criteria andZfbzEqualTo(Byte value) {
            addCriterion("zfbz =", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzNotEqualTo(Byte value) {
            addCriterion("zfbz <>", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzGreaterThan(Byte value) {
            addCriterion("zfbz >", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzGreaterThanOrEqualTo(Byte value) {
            addCriterion("zfbz >=", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzLessThan(Byte value) {
            addCriterion("zfbz <", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzLessThanOrEqualTo(Byte value) {
            addCriterion("zfbz <=", value, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzIn(List<Byte> values) {
            addCriterion("zfbz in", values, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzNotIn(List<Byte> values) {
            addCriterion("zfbz not in", values, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzBetween(Byte value1, Byte value2) {
            addCriterion("zfbz between", value1, value2, "zfbz");
            return (Criteria) this;
        }

        public Criteria andZfbzNotBetween(Byte value1, Byte value2) {
            addCriterion("zfbz not between", value1, value2, "zfbz");
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

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Long value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Long value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Long value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Long value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Long value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Long> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Long> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Long value1, Long value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Long value1, Long value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
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