package org.informationsystem.ismsuite.modeler.process.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.structuredpntypemodel.Linker;
import org.pnml.tools.epnk.structuredpntypemodel.SymbolDef;
import org.pnml.tools.epnk.structuredpntypemodel.SymbolUse;
import org.pnml.tools.epnk.structuredpntypemodel.SymbolUseMapping;

public class PNIDLinker extends EObjectImpl implements Linker {
	
	@Override
	public SymbolUseMapping getglobalLinks(PetriNet petrinet) {
		
		SymbolMap map = new SymbolMap();
		return map;
	}

}


class SymbolMap extends EObjectImpl implements SymbolUseMapping{
	
	Map<SymbolUse,SymbolDef> map = new HashMap<SymbolUse,SymbolDef>();

	@Override
	public SymbolDef getSymbolDef(SymbolUse symbolUse) {
		return map.get(symbolUse);
	}

	@Override
	public EList<SymbolUse> getSymbolUses() {
		return new BasicEList<SymbolUse>(map.keySet());
	}
	
	void addSymbol(SymbolUse use, SymbolDef def) {
		map.put(use, def);
	}
}

