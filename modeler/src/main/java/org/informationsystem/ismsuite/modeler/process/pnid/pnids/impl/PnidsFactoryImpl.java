/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PnidsFactoryImpl extends EFactoryImpl implements PnidsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PnidsFactory init() {
		try {
			PnidsFactory thePnidsFactory = (PnidsFactory)EPackage.Registry.INSTANCE.getEFactory(PnidsPackage.eNS_URI);
			if (thePnidsFactory != null) {
				return thePnidsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PnidsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PnidsPackage.PNID: return createPNID();
			case PnidsPackage.PLACE: return createPlace();
			case PnidsPackage.ENTITY_TYPE_LABEL: return createEntityTypeLabel();
			case PnidsPackage.ENTITY_TYPE_SEQUENCE: return createEntityTypeSequence();
			case PnidsPackage.ARC: return createArc();
			case PnidsPackage.VARIABLE_INSCRIPTION_LABEL: return createVariableInscriptionLabel();
			case PnidsPackage.TOKEN: return createToken();
			case PnidsPackage.ENTITY_TYPE: return createEntityType();
			case PnidsPackage.VARIABLE: return createVariable();
			case PnidsPackage.VARIABLE_SEQUENCE: return createVariableSequence();
			case PnidsPackage.ENTITY: return createEntity();
			case PnidsPackage.PNID_MARKING: return createPNIDMarking();
			case PnidsPackage.TOKEN_BAG: return createTokenBag();
			case PnidsPackage.TRANSACTION: return createTransaction();
			case PnidsPackage.TRANSITION: return createTransition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PNID createPNID() {
		PNIDImpl pnid = new PNIDImpl();
		return pnid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Place createPlace() {
		PlaceImpl place = new PlaceImpl();
		return place;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityTypeLabel createEntityTypeLabel() {
		EntityTypeLabelImpl entityTypeLabel = new EntityTypeLabelImpl();
		return entityTypeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityTypeSequence createEntityTypeSequence() {
		EntityTypeSequenceImpl entityTypeSequence = new EntityTypeSequenceImpl();
		return entityTypeSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Arc createArc() {
		ArcImpl arc = new ArcImpl();
		return arc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableInscriptionLabel createVariableInscriptionLabel() {
		VariableInscriptionLabelImpl variableInscriptionLabel = new VariableInscriptionLabelImpl();
		return variableInscriptionLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Token createToken() {
		TokenImpl token = new TokenImpl();
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityType createEntityType() {
		EntityTypeImpl entityType = new EntityTypeImpl();
		return entityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableSequence createVariableSequence() {
		VariableSequenceImpl variableSequence = new VariableSequenceImpl();
		return variableSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Entity createEntity() {
		EntityImpl entity = new EntityImpl();
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PNIDMarking createPNIDMarking() {
		PNIDMarkingImpl pnidMarking = new PNIDMarkingImpl();
		return pnidMarking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TokenBag createTokenBag() {
		TokenBagImpl tokenBag = new TokenBagImpl();
		return tokenBag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transaction createTransaction() {
		TransactionImpl transaction = new TransactionImpl();
		return transaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PnidsPackage getPnidsPackage() {
		return (PnidsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PnidsPackage getPackage() {
		return PnidsPackage.eINSTANCE;
	}

} //PnidsFactoryImpl
