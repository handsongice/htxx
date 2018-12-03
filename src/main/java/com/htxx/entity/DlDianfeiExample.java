package com.htxx.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DlDianfeiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlDianfeiExample() {
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

        public Criteria andIdentityFieldIsNull() {
            addCriterion("identity_field is null");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldIsNotNull() {
            addCriterion("identity_field is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldEqualTo(String value) {
            addCriterion("identity_field =", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldNotEqualTo(String value) {
            addCriterion("identity_field <>", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldGreaterThan(String value) {
            addCriterion("identity_field >", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldGreaterThanOrEqualTo(String value) {
            addCriterion("identity_field >=", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldLessThan(String value) {
            addCriterion("identity_field <", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldLessThanOrEqualTo(String value) {
            addCriterion("identity_field <=", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldLike(String value) {
            addCriterion("identity_field like", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldNotLike(String value) {
            addCriterion("identity_field not like", value, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldIn(List<String> values) {
            addCriterion("identity_field in", values, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldNotIn(List<String> values) {
            addCriterion("identity_field not in", values, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldBetween(String value1, String value2) {
            addCriterion("identity_field between", value1, value2, "identityField");
            return (Criteria) this;
        }

        public Criteria andIdentityFieldNotBetween(String value1, String value2) {
            addCriterion("identity_field not between", value1, value2, "identityField");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Byte value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Byte value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Byte value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Byte value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Byte value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Byte> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Byte> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Byte value1, Byte value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDjTypeIsNull() {
            addCriterion("dj_type is null");
            return (Criteria) this;
        }

        public Criteria andDjTypeIsNotNull() {
            addCriterion("dj_type is not null");
            return (Criteria) this;
        }

        public Criteria andDjTypeEqualTo(Byte value) {
            addCriterion("dj_type =", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeNotEqualTo(Byte value) {
            addCriterion("dj_type <>", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeGreaterThan(Byte value) {
            addCriterion("dj_type >", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("dj_type >=", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeLessThan(Byte value) {
            addCriterion("dj_type <", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeLessThanOrEqualTo(Byte value) {
            addCriterion("dj_type <=", value, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeIn(List<Byte> values) {
            addCriterion("dj_type in", values, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeNotIn(List<Byte> values) {
            addCriterion("dj_type not in", values, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeBetween(Byte value1, Byte value2) {
            addCriterion("dj_type between", value1, value2, "djType");
            return (Criteria) this;
        }

        public Criteria andDjTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("dj_type not between", value1, value2, "djType");
            return (Criteria) this;
        }

        public Criteria andMonthYearIsNull() {
            addCriterion("month_year is null");
            return (Criteria) this;
        }

        public Criteria andMonthYearIsNotNull() {
            addCriterion("month_year is not null");
            return (Criteria) this;
        }

        public Criteria andMonthYearEqualTo(String value) {
            addCriterion("month_year =", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearNotEqualTo(String value) {
            addCriterion("month_year <>", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearGreaterThan(String value) {
            addCriterion("month_year >", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearGreaterThanOrEqualTo(String value) {
            addCriterion("month_year >=", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearLessThan(String value) {
            addCriterion("month_year <", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearLessThanOrEqualTo(String value) {
            addCriterion("month_year <=", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearLike(String value) {
            addCriterion("month_year like", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearNotLike(String value) {
            addCriterion("month_year not like", value, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearIn(List<String> values) {
            addCriterion("month_year in", values, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearNotIn(List<String> values) {
            addCriterion("month_year not in", values, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearBetween(String value1, String value2) {
            addCriterion("month_year between", value1, value2, "monthYear");
            return (Criteria) this;
        }

        public Criteria andMonthYearNotBetween(String value1, String value2) {
            addCriterion("month_year not between", value1, value2, "monthYear");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNull() {
            addCriterion("user_num is null");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNotNull() {
            addCriterion("user_num is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumEqualTo(String value) {
            addCriterion("user_num =", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotEqualTo(String value) {
            addCriterion("user_num <>", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThan(String value) {
            addCriterion("user_num >", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThanOrEqualTo(String value) {
            addCriterion("user_num >=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThan(String value) {
            addCriterion("user_num <", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThanOrEqualTo(String value) {
            addCriterion("user_num <=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLike(String value) {
            addCriterion("user_num like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotLike(String value) {
            addCriterion("user_num not like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIn(List<String> values) {
            addCriterion("user_num in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotIn(List<String> values) {
            addCriterion("user_num not in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumBetween(String value1, String value2) {
            addCriterion("user_num between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotBetween(String value1, String value2) {
            addCriterion("user_num not between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andExportNnumIsNull() {
            addCriterion("export_nnum is null");
            return (Criteria) this;
        }

        public Criteria andExportNnumIsNotNull() {
            addCriterion("export_nnum is not null");
            return (Criteria) this;
        }

        public Criteria andExportNnumEqualTo(String value) {
            addCriterion("export_nnum =", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumNotEqualTo(String value) {
            addCriterion("export_nnum <>", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumGreaterThan(String value) {
            addCriterion("export_nnum >", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumGreaterThanOrEqualTo(String value) {
            addCriterion("export_nnum >=", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumLessThan(String value) {
            addCriterion("export_nnum <", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumLessThanOrEqualTo(String value) {
            addCriterion("export_nnum <=", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumLike(String value) {
            addCriterion("export_nnum like", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumNotLike(String value) {
            addCriterion("export_nnum not like", value, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumIn(List<String> values) {
            addCriterion("export_nnum in", values, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumNotIn(List<String> values) {
            addCriterion("export_nnum not in", values, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumBetween(String value1, String value2) {
            addCriterion("export_nnum between", value1, value2, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andExportNnumNotBetween(String value1, String value2) {
            addCriterion("export_nnum not between", value1, value2, "exportNnum");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoIsNull() {
            addCriterion("user_tax_no is null");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoIsNotNull() {
            addCriterion("user_tax_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoEqualTo(String value) {
            addCriterion("user_tax_no =", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoNotEqualTo(String value) {
            addCriterion("user_tax_no <>", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoGreaterThan(String value) {
            addCriterion("user_tax_no >", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoGreaterThanOrEqualTo(String value) {
            addCriterion("user_tax_no >=", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoLessThan(String value) {
            addCriterion("user_tax_no <", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoLessThanOrEqualTo(String value) {
            addCriterion("user_tax_no <=", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoLike(String value) {
            addCriterion("user_tax_no like", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoNotLike(String value) {
            addCriterion("user_tax_no not like", value, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoIn(List<String> values) {
            addCriterion("user_tax_no in", values, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoNotIn(List<String> values) {
            addCriterion("user_tax_no not in", values, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoBetween(String value1, String value2) {
            addCriterion("user_tax_no between", value1, value2, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserTaxNoNotBetween(String value1, String value2) {
            addCriterion("user_tax_no not between", value1, value2, "userTaxNo");
            return (Criteria) this;
        }

        public Criteria andUserAddrIsNull() {
            addCriterion("user_addr is null");
            return (Criteria) this;
        }

        public Criteria andUserAddrIsNotNull() {
            addCriterion("user_addr is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddrEqualTo(String value) {
            addCriterion("user_addr =", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrNotEqualTo(String value) {
            addCriterion("user_addr <>", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrGreaterThan(String value) {
            addCriterion("user_addr >", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrGreaterThanOrEqualTo(String value) {
            addCriterion("user_addr >=", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrLessThan(String value) {
            addCriterion("user_addr <", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrLessThanOrEqualTo(String value) {
            addCriterion("user_addr <=", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrLike(String value) {
            addCriterion("user_addr like", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrNotLike(String value) {
            addCriterion("user_addr not like", value, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrIn(List<String> values) {
            addCriterion("user_addr in", values, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrNotIn(List<String> values) {
            addCriterion("user_addr not in", values, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrBetween(String value1, String value2) {
            addCriterion("user_addr between", value1, value2, "userAddr");
            return (Criteria) this;
        }

        public Criteria andUserAddrNotBetween(String value1, String value2) {
            addCriterion("user_addr not between", value1, value2, "userAddr");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxIsNull() {
            addCriterion("total_money_include_tax is null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxIsNotNull() {
            addCriterion("total_money_include_tax is not null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxEqualTo(BigDecimal value) {
            addCriterion("total_money_include_tax =", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxNotEqualTo(BigDecimal value) {
            addCriterion("total_money_include_tax <>", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxGreaterThan(BigDecimal value) {
            addCriterion("total_money_include_tax >", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money_include_tax >=", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxLessThan(BigDecimal value) {
            addCriterion("total_money_include_tax <", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money_include_tax <=", value, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxIn(List<BigDecimal> values) {
            addCriterion("total_money_include_tax in", values, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxNotIn(List<BigDecimal> values) {
            addCriterion("total_money_include_tax not in", values, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money_include_tax between", value1, value2, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIncludeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money_include_tax not between", value1, value2, "totalMoneyIncludeTax");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoIsNull() {
            addCriterion("user_bankInfo is null");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoIsNotNull() {
            addCriterion("user_bankInfo is not null");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoEqualTo(String value) {
            addCriterion("user_bankInfo =", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoNotEqualTo(String value) {
            addCriterion("user_bankInfo <>", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoGreaterThan(String value) {
            addCriterion("user_bankInfo >", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoGreaterThanOrEqualTo(String value) {
            addCriterion("user_bankInfo >=", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoLessThan(String value) {
            addCriterion("user_bankInfo <", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoLessThanOrEqualTo(String value) {
            addCriterion("user_bankInfo <=", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoLike(String value) {
            addCriterion("user_bankInfo like", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoNotLike(String value) {
            addCriterion("user_bankInfo not like", value, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoIn(List<String> values) {
            addCriterion("user_bankInfo in", values, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoNotIn(List<String> values) {
            addCriterion("user_bankInfo not in", values, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoBetween(String value1, String value2) {
            addCriterion("user_bankInfo between", value1, value2, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andUserBankinfoNotBetween(String value1, String value2) {
            addCriterion("user_bankInfo not between", value1, value2, "userBankinfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoIsNull() {
            addCriterion("record_info is null");
            return (Criteria) this;
        }

        public Criteria andRecordInfoIsNotNull() {
            addCriterion("record_info is not null");
            return (Criteria) this;
        }

        public Criteria andRecordInfoEqualTo(String value) {
            addCriterion("record_info =", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoNotEqualTo(String value) {
            addCriterion("record_info <>", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoGreaterThan(String value) {
            addCriterion("record_info >", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoGreaterThanOrEqualTo(String value) {
            addCriterion("record_info >=", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoLessThan(String value) {
            addCriterion("record_info <", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoLessThanOrEqualTo(String value) {
            addCriterion("record_info <=", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoLike(String value) {
            addCriterion("record_info like", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoNotLike(String value) {
            addCriterion("record_info not like", value, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoIn(List<String> values) {
            addCriterion("record_info in", values, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoNotIn(List<String> values) {
            addCriterion("record_info not in", values, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoBetween(String value1, String value2) {
            addCriterion("record_info between", value1, value2, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andRecordInfoNotBetween(String value1, String value2) {
            addCriterion("record_info not between", value1, value2, "recordInfo");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Long value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Long value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Long value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Long value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Long value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Long value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Long> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Long> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Long value1, Long value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Long value1, Long value2) {
            addCriterion("operator not between", value1, value2, "operator");
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