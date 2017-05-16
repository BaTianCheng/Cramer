package com.cw.cramer.module.msg.entity;

import java.util.ArrayList;
import java.util.List;

public class MsgWebMailStorageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsgWebMailStorageExample() {
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
            addCriterion("web_mail_storage.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("web_mail_storage.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("web_mail_storage.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("web_mail_storage.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("web_mail_storage.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("web_mail_storage.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("web_mail_storage.type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("web_mail_storage.type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("web_mail_storage.type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("web_mail_storage.type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("web_mail_storage.type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("web_mail_storage.type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("web_mail_storage.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("web_mail_storage.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("web_mail_storage.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("web_mail_storage.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("web_mail_storage.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("web_mail_storage.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("web_mail_storage.tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("web_mail_storage.tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(Integer value) {
            addCriterion("web_mail_storage.tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(Integer value) {
            addCriterion("web_mail_storage.tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(Integer value) {
            addCriterion("web_mail_storage.tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<Integer> values) {
            addCriterion("web_mail_storage.tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("web_mail_storage.owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("web_mail_storage.owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(Integer value) {
            addCriterion("web_mail_storage.owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(Integer value) {
            addCriterion("web_mail_storage.owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(Integer value) {
            addCriterion("web_mail_storage.owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<Integer> values) {
            addCriterion("web_mail_storage.owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andMailIdIsNull() {
            addCriterion("web_mail_storage.mail_id is null");
            return (Criteria) this;
        }

        public Criteria andMailIdIsNotNull() {
            addCriterion("web_mail_storage.mail_id is not null");
            return (Criteria) this;
        }

        public Criteria andMailIdEqualTo(Integer value) {
            addCriterion("web_mail_storage.mail_id =", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.mail_id <>", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdGreaterThan(Integer value) {
            addCriterion("web_mail_storage.mail_id >", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.mail_id >=", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdLessThan(Integer value) {
            addCriterion("web_mail_storage.mail_id <", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.mail_id <=", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdIn(List<Integer> values) {
            addCriterion("web_mail_storage.mail_id in", values, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.mail_id not in", values, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.mail_id between", value1, value2, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.mail_id not between", value1, value2, "mailId");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("web_mail_storage.method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("web_mail_storage.method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(Integer value) {
            addCriterion("web_mail_storage.method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(Integer value) {
            addCriterion("web_mail_storage.method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(Integer value) {
            addCriterion("web_mail_storage.method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(Integer value) {
            addCriterion("web_mail_storage.method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(Integer value) {
            addCriterion("web_mail_storage.method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<Integer> values) {
            addCriterion("web_mail_storage.method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<Integer> values) {
            addCriterion("web_mail_storage.method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("web_mail_storage.method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("web_mail_storage.remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("web_mail_storage.remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("web_mail_storage.remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("web_mail_storage.remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("web_mail_storage.remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("web_mail_storage.remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("web_mail_storage.remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("web_mail_storage.remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("web_mail_storage.remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("web_mail_storage.remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("web_mail_storage.remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("web_mail_storage.remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("web_mail_storage.remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("web_mail_storage.remarks not between", value1, value2, "remarks");
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