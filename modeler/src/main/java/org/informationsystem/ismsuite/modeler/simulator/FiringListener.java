package org.informationsystem.ismsuite.modeler.simulator;

import java.util.Map;

import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.World;

public interface FiringListener {

	public void onBindingFired(PNIDBinding fired, FirstOrderLogicWorld world, Map<PNIDBinding, World> enabledBindings, Map<PNIDBinding, String> disabledBindings);
}
