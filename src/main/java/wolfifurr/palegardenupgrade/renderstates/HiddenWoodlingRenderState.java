package wolfifurr.palegardenupgrade.renderstates;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import wolfifurr.palegardenupgrade.animations.HiddelWoodlingAnimations;

public class HiddenWoodlingRenderState extends LivingEntityRenderState {
    public final AnimationState IDLE = new AnimationState ();
    public final AnimationState ATTACK = new AnimationState ();
}
