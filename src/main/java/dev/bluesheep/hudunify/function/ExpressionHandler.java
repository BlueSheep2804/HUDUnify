package dev.bluesheep.hudunify.function;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.FunctionIfc;
import com.ezylang.evalex.parser.ParseException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionHandler {
    public static ExpressionHandler INSTANCE = new ExpressionHandler();
    private static final Pattern DOLLAR_EXPR = Pattern.compile("\\$(.+?)\\$");
    private final ExpressionConfiguration configuration = ExpressionConfiguration.builder()
            .singleQuoteStringLiteralsAllowed(true)
            .build();

    private ExpressionHandler() {}

    public void register(String name, FunctionIfc function) {
        configuration.withAdditionalFunctions(
                Map.entry(name, function)
        );
    }

    @SafeVarargs
    public final void registerAll(Map.Entry<String, FunctionIfc>... functions) {
        configuration.withAdditionalFunctions(functions);
    }

    public String parse(String input) {
        Matcher matcher = DOLLAR_EXPR.matcher(input);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String expr = matcher.group(1);
            try {
                EvaluationValue resolved = new Expression(expr, configuration).evaluate();
                matcher.appendReplacement(result, resolved.getStringValue());
            } catch (EvaluationException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        matcher.appendTail(result);
        return result.toString();
    }
}
