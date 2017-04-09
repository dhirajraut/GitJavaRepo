package com.intertek.dao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;

/**
 * This class is used to do escape chars _ and %
 * 
 * @author Eric.Nguyen
 */
public class ILikeExpression extends LikeExpression {
    private static final long serialVersionUID = 3804996325539662415L;

    public ILikeExpression(String propertyName, Object value) {
        super(propertyName, value);
    }

    public ILikeExpression(String propertyName, String value, MatchMode matchMode) {
        super(propertyName, value, matchMode);
    }

    public ILikeExpression(String propertyName, String value, Character escapeChar) {
        super(propertyName, value);
    }

    @Override
    protected String lhs(Dialect dialect, String column) {
        return dialect.getLowercaseFunction() + '(' + column + ')';
    }

    @Override
    protected String typedValue(String value) {
        return super.typedValue(value).toLowerCase();
    }
}
