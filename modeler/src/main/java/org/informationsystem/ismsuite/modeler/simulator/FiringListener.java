package org.informationsystem.ismsuite.modeler.simulator;

import java.util.Map;
import java.util.Set;

import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

public interface FiringListener {

	public void onBindingFired(PNIDBinding fired, FirstOrderLogicWorld world, Set<PNIDBinding> enabledBindings, Map<PNIDBinding, String> disabledBindings, Map<PNIDBinding, String> warnedBindings);
}
