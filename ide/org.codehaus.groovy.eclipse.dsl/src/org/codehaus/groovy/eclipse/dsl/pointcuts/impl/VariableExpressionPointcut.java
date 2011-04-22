/*******************************************************************************
 * Copyright (c) 2011 Codehaus.org, SpringSource, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Andrew Eisenberg - Initial implemenation
 *******************************************************************************/
package org.codehaus.groovy.eclipse.dsl.pointcuts.impl;

import java.util.Collection;

import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.dsl.pointcuts.GroovyDSLDContext;

/**
 * the match returns true if the pattern passed in is a variable expression
 * that matches the contained argument
 * @author andrew
 * @created Feb 11, 2011
 */
public class VariableExpressionPointcut extends FilteringPointcut<Expression> {

    public VariableExpressionPointcut(String containerIdentifier, String pointcutName) {
        super(containerIdentifier, pointcutName, Expression.class);
    }

    
    /**
     * Ignore toMatch and use the current node instead
     */
    @Override
    public Collection<?> matches(GroovyDSLDContext pattern, Object toMatch) {
        return super.matches(pattern, pattern.getCurrentScope().getCurrentNode());
    }

    @Override
    protected Expression filterObject(Expression result, GroovyDSLDContext context, String firstArgAsString) {
        if (result instanceof VariableExpression || result instanceof ConstantExpression) {
            if (firstArgAsString == null || result.getText().equals(firstArgAsString)) {
                return result;
            }
        }
        return null;
    }
}
