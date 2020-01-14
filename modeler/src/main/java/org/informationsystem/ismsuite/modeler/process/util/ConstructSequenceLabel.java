
package org.informationsystem.ismsuite.modeler.process.util;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionBaseVisitor;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionParser;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionParser.VariableContext;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionParser.Variable_listContext;

public class ConstructSequenceLabel extends VariableDescriptionBaseVisitor<VariableSequence> {

	/**
	 * count? variable_list? EOF;
	 */
	@Override
	public VariableSequence visitSequence(VariableDescriptionParser.SequenceContext ctx) {
		
		VariableSequence sequence = PnidsFactory.eINSTANCE.createVariableSequence();
		int multiplicity = 1;
		
		if (ctx.amount() != null) {
			multiplicity = Integer.parseInt(ctx.amount().getText());
		}
		
		if (ctx.variable() != null) {
			insertVariable(sequence, ctx.variable());
		} else if (ctx.variable_list()!= null) {
			for (VariableContext v: ctx.variable_list().variable()) {
				insertVariable(sequence, v);
			}
		}
		
		sequence.setMultiplicity(multiplicity);
		
		return sequence;
	}
	
	private void insertVariable(VariableSequence sequence, VariableContext variable) {
		Variable v = PnidsFactory.eINSTANCE.createVariable();
		v.setText(variable.getText());
		sequence.getVariable().add(v);
	}
	
}
