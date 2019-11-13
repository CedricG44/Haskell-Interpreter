package fr.imt.haskell.interpreter.parser;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.ConditionalExpression;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Divide;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Minus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Plus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Times;
import fr.imt.haskell.interpreter.ast.builtin.logicals.*;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.parser.antlr.HaskellBaseVisitor;
import fr.imt.haskell.interpreter.parser.antlr.HaskellParser;

import java.util.List;
import java.util.stream.Collectors;

public class HaskellProgramVisitor extends HaskellBaseVisitor<Expression> {

  @Override
  public Expression visitProgram(HaskellParser.ProgramContext ctx) {
    return super.visitProgram(ctx);
  }

  @Override
  public Expression visitFunction(HaskellParser.FunctionContext ctx) {
    return super.visitFunction(ctx);
  }

  @Override
  public Expression visitBinaryExpression(HaskellParser.BinaryExpressionContext ctx) {
    List<Expression> expressions =
        ctx.expression().stream().map(this::visit).collect(Collectors.toList());

    if (ctx.PLUS() != null) {
      return new Plus(expressions.get(0), expressions.get(1));
    } else if (ctx.TIMES() != null) {
      return new Times(expressions.get(0), expressions.get(1));
    } else if (ctx.DIVIDE() != null) {
      return new Divide(expressions.get(0), expressions.get(1));
    } else if (ctx.AND() != null) {
      return new And(expressions.get(0), expressions.get(1));
    } else if (ctx.OR() != null) {
      return new Or(expressions.get(0), expressions.get(1));
    } else if (ctx.EQUAL() != null) {
      return new Equal(expressions.get(0), expressions.get(1));
    } else if (ctx.GREATERTHAN() != null) {
      return new GreaterThan(expressions.get(0), expressions.get(1));
    } else if (ctx.LESSTHAN() != null) {
      return new LessThan(expressions.get(0), expressions.get(1));
    } else if (ctx.GREATERTHANOREQUAL() != null) {
      return new GreaterThanOrEqual(expressions.get(0), expressions.get(1));
    } else if (ctx.LESSTHANOREQUAL() != null) {
      return new LessThanOrEqual(expressions.get(0), expressions.get(1));
    }
    return super.visitBinaryExpression(ctx);
  }

  @Override
  public Expression visitConditionalExpression(HaskellParser.ConditionalExpressionContext ctx) {
    List<Expression> expressions =
        ctx.expression().stream().map(this::visit).collect(Collectors.toList());
    return new ConditionalExpression(expressions.get(0), expressions.get(1), expressions.get(2));
  }

  @Override
  public Expression visitUnaryExpression(HaskellParser.UnaryExpressionContext ctx) {
    if (ctx.MINUS() != null) {
      return new Minus(visit(ctx.expression()));
    } else if (ctx.NOT() != null) {
      return new Not(visit(ctx.expression()));
    }
    return super.visitUnaryExpression(ctx);
  }

  @Override
  public Expression visitApplication(HaskellParser.ApplicationContext ctx) {
    List<Expression> expressions =
        ctx.expression().stream().map(this::visit).collect(Collectors.toList());
    return new Application(expressions.get(0), expressions.get(1));
  }

  @Override
  public Expression visitLambda(HaskellParser.LambdaContext ctx) {
    return super.visitLambda(ctx);
  }

  @Override
  public Expression visitVar(HaskellParser.VarContext ctx) {
    return super.visitVariable(ctx.variable());
  }

  @Override
  public Expression visitConst(HaskellParser.ConstContext ctx) {
    return super.visitConstant(ctx.constant());
  }

  @Override
  public Expression visitVariable(HaskellParser.VariableContext ctx) {
    return new Variable(ctx.getText());
  }

  @Override
  public Expression visitConstant(HaskellParser.ConstantContext ctx) {
    if (!ctx.number().isEmpty()) {
      return new Number(Integer.parseInt(ctx.getText()));
    } else if (!ctx.bool().isEmpty()) {
      return new Boolean(java.lang.Boolean.parseBoolean(ctx.getText()));
    }
    return super.visitConstant(ctx);
  }

  @Override
  public Expression visitNumber(HaskellParser.NumberContext ctx) {
    return new Number(Integer.parseInt(ctx.getText()));
  }

  @Override
  public Expression visitBool(HaskellParser.BoolContext ctx) {
    return new Boolean(java.lang.Boolean.parseBoolean(ctx.getText()));
  }
}
