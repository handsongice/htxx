package com.htxx.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DlZiyoupiaoDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlZiyoupiaoDetailExample() {
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

        public Criteria andMainIdIsNull() {
            addCriterion("main_id is null");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNotNull() {
            addCriterion("main_id is not null");
            return (Criteria) this;
        }

        public Criteria andMainIdEqualTo(Long value) {
            addCriterion("main_id =", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotEqualTo(Long value) {
            addCriterion("main_id <>", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThan(Long value) {
            addCriterion("main_id >", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("main_id >=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThan(Long value) {
            addCriterion("main_id <", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThanOrEqualTo(Long value) {
            addCriterion("main_id <=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdIn(List<Long> values) {
            addCriterion("main_id in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotIn(List<Long> values) {
            addCriterion("main_id not in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdBetween(Long value1, Long value2) {
            addCriterion("main_id between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotBetween(Long value1, Long value2) {
            addCriterion("main_id not between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmIsNull() {
            addCriterion("goods_ssflbm is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmIsNotNull() {
            addCriterion("goods_ssflbm is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmEqualTo(String value) {
            addCriterion("goods_ssflbm =", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmNotEqualTo(String value) {
            addCriterion("goods_ssflbm <>", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmGreaterThan(String value) {
            addCriterion("goods_ssflbm >", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmGreaterThanOrEqualTo(String value) {
            addCriterion("goods_ssflbm >=", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmLessThan(String value) {
            addCriterion("goods_ssflbm <", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmLessThanOrEqualTo(String value) {
            addCriterion("goods_ssflbm <=", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmLike(String value) {
            addCriterion("goods_ssflbm like", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmNotLike(String value) {
            addCriterion("goods_ssflbm not like", value, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmIn(List<String> values) {
            addCriterion("goods_ssflbm in", values, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmNotIn(List<String> values) {
            addCriterion("goods_ssflbm not in", values, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmBetween(String value1, String value2) {
            addCriterion("goods_ssflbm between", value1, value2, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsSsflbmNotBetween(String value1, String value2) {
            addCriterion("goods_ssflbm not between", value1, value2, "goodsSsflbm");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIsNull() {
            addCriterion("goods_unit is null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIsNotNull() {
            addCriterion("goods_unit is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitEqualTo(String value) {
            addCriterion("goods_unit =", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotEqualTo(String value) {
            addCriterion("goods_unit <>", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitGreaterThan(String value) {
            addCriterion("goods_unit >", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitGreaterThanOrEqualTo(String value) {
            addCriterion("goods_unit >=", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitLessThan(String value) {
            addCriterion("goods_unit <", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitLessThanOrEqualTo(String value) {
            addCriterion("goods_unit <=", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitLike(String value) {
            addCriterion("goods_unit like", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotLike(String value) {
            addCriterion("goods_unit not like", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIn(List<String> values) {
            addCriterion("goods_unit in", values, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotIn(List<String> values) {
            addCriterion("goods_unit not in", values, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitBetween(String value1, String value2) {
            addCriterion("goods_unit between", value1, value2, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotBetween(String value1, String value2) {
            addCriterion("goods_unit not between", value1, value2, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsModeIsNull() {
            addCriterion("goods_mode is null");
            return (Criteria) this;
        }

        public Criteria andGoodsModeIsNotNull() {
            addCriterion("goods_mode is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsModeEqualTo(String value) {
            addCriterion("goods_mode =", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeNotEqualTo(String value) {
            addCriterion("goods_mode <>", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeGreaterThan(String value) {
            addCriterion("goods_mode >", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_mode >=", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeLessThan(String value) {
            addCriterion("goods_mode <", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeLessThanOrEqualTo(String value) {
            addCriterion("goods_mode <=", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeLike(String value) {
            addCriterion("goods_mode like", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeNotLike(String value) {
            addCriterion("goods_mode not like", value, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeIn(List<String> values) {
            addCriterion("goods_mode in", values, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeNotIn(List<String> values) {
            addCriterion("goods_mode not in", values, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeBetween(String value1, String value2) {
            addCriterion("goods_mode between", value1, value2, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsModeNotBetween(String value1, String value2) {
            addCriterion("goods_mode not between", value1, value2, "goodsMode");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNull() {
            addCriterion("goods_amount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNotNull() {
            addCriterion("goods_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountEqualTo(BigDecimal value) {
            addCriterion("goods_amount =", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotEqualTo(BigDecimal value) {
            addCriterion("goods_amount <>", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThan(BigDecimal value) {
            addCriterion("goods_amount >", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_amount >=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThan(BigDecimal value) {
            addCriterion("goods_amount <", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_amount <=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIn(List<BigDecimal> values) {
            addCriterion("goods_amount in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotIn(List<BigDecimal> values) {
            addCriterion("goods_amount not in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_amount between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_amount not between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxIsNull() {
            addCriterion("money_include_tax is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxIsNotNull() {
            addCriterion("money_include_tax is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxEqualTo(BigDecimal value) {
            addCriterion("money_include_tax =", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxNotEqualTo(BigDecimal value) {
            addCriterion("money_include_tax <>", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxGreaterThan(BigDecimal value) {
            addCriterion("money_include_tax >", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money_include_tax >=", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxLessThan(BigDecimal value) {
            addCriterion("money_include_tax <", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money_include_tax <=", value, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxIn(List<BigDecimal> values) {
            addCriterion("money_include_tax in", values, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxNotIn(List<BigDecimal> values) {
            addCriterion("money_include_tax not in", values, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_include_tax between", value1, value2, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andMoneyIncludeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_include_tax not between", value1, value2, "moneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(BigDecimal value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(BigDecimal value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(BigDecimal value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<BigDecimal> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andYhzelxIsNull() {
            addCriterion("yhzelx is null");
            return (Criteria) this;
        }

        public Criteria andYhzelxIsNotNull() {
            addCriterion("yhzelx is not null");
            return (Criteria) this;
        }

        public Criteria andYhzelxEqualTo(Integer value) {
            addCriterion("yhzelx =", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxNotEqualTo(Integer value) {
            addCriterion("yhzelx <>", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxGreaterThan(Integer value) {
            addCriterion("yhzelx >", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxGreaterThanOrEqualTo(Integer value) {
            addCriterion("yhzelx >=", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxLessThan(Integer value) {
            addCriterion("yhzelx <", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxLessThanOrEqualTo(Integer value) {
            addCriterion("yhzelx <=", value, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxIn(List<Integer> values) {
            addCriterion("yhzelx in", values, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxNotIn(List<Integer> values) {
            addCriterion("yhzelx not in", values, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxBetween(Integer value1, Integer value2) {
            addCriterion("yhzelx between", value1, value2, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzelxNotBetween(Integer value1, Integer value2) {
            addCriterion("yhzelx not between", value1, value2, "yhzelx");
            return (Criteria) this;
        }

        public Criteria andYhzenrIsNull() {
            addCriterion("yhzenr is null");
            return (Criteria) this;
        }

        public Criteria andYhzenrIsNotNull() {
            addCriterion("yhzenr is not null");
            return (Criteria) this;
        }

        public Criteria andYhzenrEqualTo(String value) {
            addCriterion("yhzenr =", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrNotEqualTo(String value) {
            addCriterion("yhzenr <>", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrGreaterThan(String value) {
            addCriterion("yhzenr >", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrGreaterThanOrEqualTo(String value) {
            addCriterion("yhzenr >=", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrLessThan(String value) {
            addCriterion("yhzenr <", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrLessThanOrEqualTo(String value) {
            addCriterion("yhzenr <=", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrLike(String value) {
            addCriterion("yhzenr like", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrNotLike(String value) {
            addCriterion("yhzenr not like", value, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrIn(List<String> values) {
            addCriterion("yhzenr in", values, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrNotIn(List<String> values) {
            addCriterion("yhzenr not in", values, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrBetween(String value1, String value2) {
            addCriterion("yhzenr between", value1, value2, "yhzenr");
            return (Criteria) this;
        }

        public Criteria andYhzenrNotBetween(String value1, String value2) {
            addCriterion("yhzenr not between", value1, value2, "yhzenr");
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