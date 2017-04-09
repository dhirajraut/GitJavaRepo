package com.intertek.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.TypedValue;

/**
 * This class is used to do escape chars _ and %
 * 
 * @author Eric.Nguyen
 */

public class LikeExpression implements Criterion {
    private static final long serialVersionUID = -4915354623219451401L;
    private final String propertyName;
    private final String value;
    private final Character escapeChar;

    public LikeExpression(String propertyName, Object value) {
        this(propertyName, value.toString());
    }

    public LikeExpression(String propertyName, String value, MatchMode matchMode) {
        this.propertyName = propertyName;
        this.value = matchMode.toMatchString(doEscape(value));
        this.escapeChar = '!';
    }

    public LikeExpression(String propertyName, String value) {
        this.propertyName = propertyName;
        this.value = doEscape(value);
        this.escapeChar = '!';
    }

    private String doEscape(String value) {
        return value.replaceAll("!", "!!").replaceAll("%", "!%").replaceAll("_", "!_");
    }

    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        Dialect dialect = criteriaQuery.getFactory().getDialect();
        String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);
        if (columns.length != 1) {
            throw new HibernateException("Like may only be used with single-column properties");
        }
        String lhs = lhs(dialect, columns[0]);
        return lhs + " like ?" + (escapeChar == null ? "" : " escape ?");
    }

    public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        return new TypedValue[] { criteriaQuery.getTypedValue(criteria, propertyName, typedValue(value)),
                                 criteriaQuery.getTypedValue(criteria, propertyName, escapeChar.toString()) };
    }

    protected String lhs(Dialect dialect, String column) {
        return column;
    }

    protected String typedValue(String value) {
        return value;
    }
}
