package dev.bluesheep.hudunify.function;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;
import dev.bluesheep.hudunify.ApiImpl;
import net.minecraft.world.entity.player.Player;

@FunctionParameter(name = "attribute")
public class PlayerFunction extends AbstractFunction {
    @Override
    public EvaluationValue evaluate(Expression expression, Token token, EvaluationValue... evaluationValues) {
        EvaluationValue attribute = evaluationValues[0];

        Player player = ApiImpl.INSTANCE.getPlayer();
        return EvaluationValue.of(
            switch (attribute.getStringValue()) {
                case "health" -> player.getHealth();
                case "health_max" -> player.getMaxHealth();
                case "health_percentage" -> (player.getHealth() / player.getMaxHealth()) * 100;

                case "pos_x" -> player.position().x;
                case "pos_y" -> player.position().y;
                case "pos_z" -> player.position().z;
                case "yaw" -> ApiImpl.INSTANCE.getCameraEntity().getXRot();
                case "pitch" -> ApiImpl.INSTANCE.getCameraEntity().getYRot();
                default -> "";
            },
            expression.getConfiguration()
        );
    }
}
