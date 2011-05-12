/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.oracle.ast.expr;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLExprImpl;
import com.alibaba.druid.sql.dialect.oracle.ast.visitor.OracleASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class OracleArrayAccessExpr extends SQLExprImpl {

    private static final long   serialVersionUID = 1L;
    private SQLExpr             ownner;
    private final List<SQLExpr> arguments        = new ArrayList<SQLExpr>();

    public OracleArrayAccessExpr(){

    }

    public SQLExpr getOwnner() {
        return this.ownner;
    }

    public void setOwnner(SQLExpr ownner) {
        this.ownner = ownner;
    }

    public List<SQLExpr> getArguments() {
        return this.arguments;
    }

    public void output(StringBuffer buf) {
        this.ownner.output(buf);
        buf.append("[");
        int i = 0;
        for (int size = this.arguments.size(); i < size; ++i) {
            if (i != 0) {
                buf.append(", ");
            }
            ((SQLExpr) this.arguments.get(i)).output(buf);
        }
        buf.append("]");
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        this.accept0((OracleASTVisitor) visitor);
    }

    protected void accept0(OracleASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.ownner);
            acceptChild(visitor, this.arguments);
        }
        visitor.endVisit(this);
    }
}
