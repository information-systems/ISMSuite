/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.*;

import org.pnml.tools.epnk.pnmlcoremodel.ID;
import org.pnml.tools.epnk.pnmlcoremodel.Label;
import org.pnml.tools.epnk.pnmlcoremodel.Node;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetType;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel;
import org.pnml.tools.epnk.structuredpntypemodel.StructuredPetriNetType;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage
 * @generated
 */
public class PnidsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PnidsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PnidsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PnidsSwitch<Adapter> modelSwitch =
		new PnidsSwitch<Adapter>() {
			@Override
			public Adapter casePNID(PNID object) {
				return createPNIDAdapter();
			}
			@Override
			public Adapter casePlace(Place object) {
				return createPlaceAdapter();
			}
			@Override
			public Adapter caseEntityTypeLabel(EntityTypeLabel object) {
				return createEntityTypeLabelAdapter();
			}
			@Override
			public Adapter caseEntityTypeSequence(EntityTypeSequence object) {
				return createEntityTypeSequenceAdapter();
			}
			@Override
			public Adapter caseArc(Arc object) {
				return createArcAdapter();
			}
			@Override
			public Adapter caseVariableInscriptionLabel(VariableInscriptionLabel object) {
				return createVariableInscriptionLabelAdapter();
			}
			@Override
			public Adapter caseToken(Token object) {
				return createTokenAdapter();
			}
			@Override
			public Adapter caseEntityType(EntityType object) {
				return createEntityTypeAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseVariableSequence(VariableSequence object) {
				return createVariableSequenceAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter casePNIDMarking(PNIDMarking object) {
				return createPNIDMarkingAdapter();
			}
			@Override
			public Adapter caseTokenBag(TokenBag object) {
				return createTokenBagAdapter();
			}
			@Override
			public Adapter casePetriNetType(PetriNetType object) {
				return createPetriNetTypeAdapter();
			}
			@Override
			public Adapter caseStructuredPetriNetType(StructuredPetriNetType object) {
				return createStructuredPetriNetTypeAdapter();
			}
			@Override
			public Adapter caseID(ID object) {
				return createIDAdapter();
			}
			@Override
			public Adapter caseObject(org.pnml.tools.epnk.pnmlcoremodel.Object object) {
				return createObjectAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter casePlaceNode(PlaceNode object) {
				return createPlaceNodeAdapter();
			}
			@Override
			public Adapter casePnmlcoremodel_Place(org.pnml.tools.epnk.pnmlcoremodel.Place object) {
				return createPnmlcoremodel_PlaceAdapter();
			}
			@Override
			public Adapter caseLabel(Label object) {
				return createLabelAdapter();
			}
			@Override
			public Adapter caseStructuredLabel(StructuredLabel object) {
				return createStructuredLabelAdapter();
			}
			@Override
			public Adapter casePnmlcoremodel_Arc(org.pnml.tools.epnk.pnmlcoremodel.Arc object) {
				return createPnmlcoremodel_ArcAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID <em>PNID</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID
	 * @generated
	 */
	public Adapter createPNIDAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place <em>Place</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place
	 * @generated
	 */
	public Adapter createPlaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel <em>Entity Type Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel
	 * @generated
	 */
	public Adapter createEntityTypeLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence <em>Entity Type Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence
	 * @generated
	 */
	public Adapter createEntityTypeSequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc
	 * @generated
	 */
	public Adapter createArcAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel <em>Variable Inscription Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel
	 * @generated
	 */
	public Adapter createVariableInscriptionLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token
	 * @generated
	 */
	public Adapter createTokenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType <em>Entity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType
	 * @generated
	 */
	public Adapter createEntityTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence <em>Variable Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence
	 * @generated
	 */
	public Adapter createVariableSequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking <em>PNID Marking</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking
	 * @generated
	 */
	public Adapter createPNIDMarkingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag <em>Token Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag
	 * @generated
	 */
	public Adapter createTokenBagAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.PetriNetType <em>Petri Net Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.PetriNetType
	 * @generated
	 */
	public Adapter createPetriNetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.structuredpntypemodel.StructuredPetriNetType <em>Structured Petri Net Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.structuredpntypemodel.StructuredPetriNetType
	 * @generated
	 */
	public Adapter createStructuredPetriNetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.ID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.ID
	 * @generated
	 */
	public Adapter createIDAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.Object
	 * @generated
	 */
	public Adapter createObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.PlaceNode <em>Place Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.PlaceNode
	 * @generated
	 */
	public Adapter createPlaceNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.Place <em>Place</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.Place
	 * @generated
	 */
	public Adapter createPnmlcoremodel_PlaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel <em>Structured Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel
	 * @generated
	 */
	public Adapter createStructuredLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.pnmlcoremodel.Arc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.pnml.tools.epnk.pnmlcoremodel.Arc
	 * @generated
	 */
	public Adapter createPnmlcoremodel_ArcAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PnidsAdapterFactory
