package com.bysj.logistics.manage.utils;

import cn.hutool.db.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/4 10:34
 * @verson
 */
public class Condition {
    public static final String OP_EQ = "=";
    public static final String OP_NEQ = "<>";
    public static final String OP_GT = ">";
    public static final String OP_GTE = ">=";
    public static final String OP_LT = "<";
    public static final String OP_LTE = "<=";
    public static final String OP_ISNULL = "is null";
    public static final String OP_NOTNUL = "is not null";
    public static final String OP_BTW = "between";
    public static final String OP_NBTW = "not between";
    public static final String OP_IN = "in";
    public static final String OP_NIN = "not in";
    public static final String OP_LIKE = "like";
    public static final String OP_NLIKE = "not like";
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria = new ArrayList();
    protected Page page;

    public Condition() {
    }

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public List<Condition.Criteria> getOredCriteria() {
        return this.oredCriteria;
    }

    public void setOredCriteria(List<Condition.Criteria> oredCriteria) {
        this.oredCriteria = oredCriteria;
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Condition.Criteria or() {
        Condition.Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public Condition.Criteria createCriteria() {
        Condition.Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    protected Condition.Criteria createCriteriaInternal() {
        Condition.Criteria criteria = new Condition.Criteria();
        return criteria;
    }

    public static class Criterion {
        private String field;
        private String operation;
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

        protected Criterion(String field, String operation) {
            this.field = field;
            this.operation = operation;
            this.condition = field + " " + operation + " ";
            this.noValue = true;
        }

        protected Criterion(String field, String operation, Object value) {
            this.field = field;
            this.operation = operation;
            this.condition = field + " " + operation + " ";
            this.value = value;
            if (value instanceof Collection) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }

        }

        protected Criterion(String field, String operation, Object firstValue, Object secondValue) {
            this.field = field;
            this.operation = operation;
            this.condition = field + " " + operation + " ";
            this.value = firstValue;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }

        public Object getValue() {
            return this.value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getSecondValue() {
            return this.secondValue;
        }

        public void setSecondValue(Object secondValue) {
            this.secondValue = secondValue;
        }

        public boolean isNoValue() {
            return this.noValue;
        }

        public void setNoValue(boolean noValue) {
            this.noValue = noValue;
        }

        public boolean isSingleValue() {
            return this.singleValue;
        }

        public void setSingleValue(boolean singleValue) {
            this.singleValue = singleValue;
        }

        public boolean isBetweenValue() {
            return this.betweenValue;
        }

        public void setBetweenValue(boolean betweenValue) {
            this.betweenValue = betweenValue;
        }

        public boolean isListValue() {
            return this.listValue;
        }

        public void setListValue(boolean listValue) {
            this.listValue = listValue;
        }

        public String getCondition() {
            return this.condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getOperation() {
            return this.operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getField() {
            return this.field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    public static class Criteria {
        protected List<Condition.Criterion> criteria = new ArrayList();

        protected Criteria() {
        }

        public List<Condition.Criterion> getCriteria() {
            return this.criteria;
        }

        public void setCriteria(List<Condition.Criterion> criteria) {
            this.criteria = criteria;
        }

        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        public Condition.Criteria andEqualTo(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "=", value));
                return this;
            }
        }

        public Condition.Criteria andNotEqualTo(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "<>", value));
                return this;
            }
        }

        public Condition.Criteria andGreaterThan(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, ">", value));
                return this;
            }
        }

        public Condition.Criteria andGreaterThanOrEqualTo(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, ">=", value));
                return this;
            }
        }

        public Condition.Criteria andLessThan(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "<", value));
                return this;
            }
        }

        public Condition.Criteria andLessThanOrEqualTo(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "<=", value));
                return this;
            }
        }

        public Condition.Criteria andIsNull(String field) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "is null"));
                return this;
            }
        }

        public Condition.Criteria andIsNotNull(String field) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "is not null"));
                return this;
            }
        }

        public Condition.Criteria andIn(String field, Iterable values) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "in", values));
                return this;
            }
        }

        public Condition.Criteria andNotIn(String field, Iterable values) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "not in", values));
                return this;
            }
        }

        public Condition.Criteria andBetween(String field, Object firstValue, Object secondValue) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "between", firstValue, secondValue));
                return this;
            }
        }

        public Condition.Criteria andNotBetween(String field, Object firstValue, Object secondValue) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "not between", firstValue, secondValue));
                return this;
            }
        }

        public Condition.Criteria andLike(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "like", value));
                return this;
            }
        }

        public Condition.Criteria andNotLike(String field, Object value) {
            if (field == null) {
                throw new RuntimeException("Value for field cannot be null");
            } else {
                this.criteria.add(new Condition.Criterion(field, "not like", value));
                return this;
            }
        }
    }
}

