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

import com.alibaba.druid.sql.dialect.oracle.ast.visitor.OracleASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class OracleDateExpr extends OracleDatetimeLiteralExpr {

    private static final long serialVersionUID = 1L;

    public OracleDateExpr(){

    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        this.accept0((OracleASTVisitor) visitor);
    }

    protected void accept0(OracleASTVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    public void output(StringBuffer buf) {
        buf.append("DATE '");

        buf.append(this.year);
        buf.append('-');
        buf.append(this.month);
        buf.append("-");
        buf.append(this.dayOfMonth);

        if ((this.hour != 0) || (this.minute != 0) || (this.second != 0)) {
            buf.append(' ');
            buf.append(this.hour);
            buf.append(":");
            buf.append(this.minute);
            if (this.second != 0) {
                buf.append(":");
                buf.append(this.second);
            }
        }

        buf.append("'");
    }
}
