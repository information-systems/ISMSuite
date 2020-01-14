/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.pnml.tools.epnk.pnmlcoremodel.PnmlcoremodelPackage;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredpntypemodelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory
 * @model kind="package"
 * @generated
 */
public interface PnidsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pnids";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.pnml.tools/epnk/types/pnids";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pnid";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PnidsPackage eINSTANCE = org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDImpl <em>PNID</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPNID()
	 * @generated
	 */
	int PNID = 0;

	/**
	 * The number of structural features of the '<em>PNID</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_FEATURE_COUNT = StructuredpntypemodelPackage.STRUCTURED_PETRI_NET_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl <em>Place</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPlace()
	 * @generated
	 */
	int PLACE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__ID = PnmlcoremodelPackage.PLACE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__NAME = PnmlcoremodelPackage.PLACE__NAME;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__TOOLSPECIFIC = PnmlcoremodelPackage.PLACE__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__GRAPHICS = PnmlcoremodelPackage.PLACE__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__UNKNOWN = PnmlcoremodelPackage.PLACE__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__OUT = PnmlcoremodelPackage.PLACE__OUT;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__IN = PnmlcoremodelPackage.PLACE__IN;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__TYPE = PnmlcoremodelPackage.PLACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initial Marking</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__INITIAL_MARKING = PnmlcoremodelPackage.PLACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Place</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_FEATURE_COUNT = PnmlcoremodelPackage.PLACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeLabelImpl <em>Entity Type Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeLabelImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityTypeLabel()
	 * @generated
	 */
	int ENTITY_TYPE_LABEL = 2;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL__TOOLSPECIFIC = StructuredpntypemodelPackage.STRUCTURED_LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL__GRAPHICS = StructuredpntypemodelPackage.STRUCTURED_LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL__UNKNOWN = StructuredpntypemodelPackage.STRUCTURED_LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL__TEXT = StructuredpntypemodelPackage.STRUCTURED_LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL__STRUCTURE = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity Type Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_LABEL_FEATURE_COUNT = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeSequenceImpl <em>Entity Type Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeSequenceImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityTypeSequence()
	 * @generated
	 */
	int ENTITY_TYPE_SEQUENCE = 3;

	/**
	 * The feature id for the '<em><b>Entity Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_SEQUENCE__ENTITY_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Entity Type Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_SEQUENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.ArcImpl <em>Arc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.ArcImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getArc()
	 * @generated
	 */
	int ARC = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__ID = PnmlcoremodelPackage.ARC__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__NAME = PnmlcoremodelPackage.ARC__NAME;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__TOOLSPECIFIC = PnmlcoremodelPackage.ARC__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__GRAPHICS = PnmlcoremodelPackage.ARC__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__UNKNOWN = PnmlcoremodelPackage.ARC__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__SOURCE = PnmlcoremodelPackage.ARC__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__TARGET = PnmlcoremodelPackage.ARC__TARGET;

	/**
	 * The feature id for the '<em><b>Inscription</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__INSCRIPTION = PnmlcoremodelPackage.ARC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Arc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC_FEATURE_COUNT = PnmlcoremodelPackage.ARC_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableInscriptionLabelImpl <em>Variable Inscription Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableInscriptionLabelImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariableInscriptionLabel()
	 * @generated
	 */
	int VARIABLE_INSCRIPTION_LABEL = 5;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL__TOOLSPECIFIC = StructuredpntypemodelPackage.STRUCTURED_LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL__GRAPHICS = StructuredpntypemodelPackage.STRUCTURED_LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL__UNKNOWN = StructuredpntypemodelPackage.STRUCTURED_LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL__TEXT = StructuredpntypemodelPackage.STRUCTURED_LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL__STRUCTURE = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Inscription Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_INSCRIPTION_LABEL_FEATURE_COUNT = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenImpl <em>Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getToken()
	 * @generated
	 */
	int TOKEN = 6;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__ENTITY = 0;

	/**
	 * The number of structural features of the '<em>Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeImpl <em>Entity Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityType()
	 * @generated
	 */
	int ENTITY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE__TOOLSPECIFIC = PnmlcoremodelPackage.LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE__GRAPHICS = PnmlcoremodelPackage.LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE__UNKNOWN = PnmlcoremodelPackage.LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE__TEXT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TYPE_FEATURE_COUNT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 8;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__TOOLSPECIFIC = PnmlcoremodelPackage.LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__GRAPHICS = PnmlcoremodelPackage.LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__UNKNOWN = PnmlcoremodelPackage.LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__TEXT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableSequenceImpl <em>Variable Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableSequenceImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariableSequence()
	 * @generated
	 */
	int VARIABLE_SEQUENCE = 9;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_SEQUENCE__VARIABLE = 0;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_SEQUENCE__MULTIPLICITY = 1;

	/**
	 * The number of structural features of the '<em>Variable Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_SEQUENCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 10;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TOOLSPECIFIC = PnmlcoremodelPackage.LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__GRAPHICS = PnmlcoremodelPackage.LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__UNKNOWN = PnmlcoremodelPackage.LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TEXT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = PnmlcoremodelPackage.LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDMarkingImpl <em>PNID Marking</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDMarkingImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPNIDMarking()
	 * @generated
	 */
	int PNID_MARKING = 11;

	/**
	 * The feature id for the '<em><b>Toolspecific</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING__TOOLSPECIFIC = StructuredpntypemodelPackage.STRUCTURED_LABEL__TOOLSPECIFIC;

	/**
	 * The feature id for the '<em><b>Graphics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING__GRAPHICS = StructuredpntypemodelPackage.STRUCTURED_LABEL__GRAPHICS;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING__UNKNOWN = StructuredpntypemodelPackage.STRUCTURED_LABEL__UNKNOWN;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING__TEXT = StructuredpntypemodelPackage.STRUCTURED_LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING__STRUCTURE = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PNID Marking</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PNID_MARKING_FEATURE_COUNT = StructuredpntypemodelPackage.STRUCTURED_LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenBagImpl <em>Token Bag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenBagImpl
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getTokenBag()
	 * @generated
	 */
	int TOKEN_BAG = 12;

	/**
	 * The feature id for the '<em><b>Token</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_BAG__TOKEN = 0;

	/**
	 * The number of structural features of the '<em>Token Bag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_BAG_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID <em>PNID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PNID</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID
	 * @generated
	 */
	EClass getPNID();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place <em>Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Place</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place
	 * @generated
	 */
	EClass getPlace();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getType()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_Type();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getInitialMarking <em>Initial Marking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initial Marking</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getInitialMarking()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_InitialMarking();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel <em>Entity Type Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Type Label</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel
	 * @generated
	 */
	EClass getEntityTypeLabel();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel#getStructure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Structure</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel#getStructure()
	 * @see #getEntityTypeLabel()
	 * @generated
	 */
	EReference getEntityTypeLabel_Structure();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence <em>Entity Type Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Type Sequence</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence
	 * @generated
	 */
	EClass getEntityTypeSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence#getEntityType <em>Entity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entity Type</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence#getEntityType()
	 * @see #getEntityTypeSequence()
	 * @generated
	 */
	EReference getEntityTypeSequence_EntityType();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arc</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc
	 * @generated
	 */
	EClass getArc();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc#getInscription <em>Inscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inscription</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc#getInscription()
	 * @see #getArc()
	 * @generated
	 */
	EReference getArc_Inscription();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel <em>Variable Inscription Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Inscription Label</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel
	 * @generated
	 */
	EClass getVariableInscriptionLabel();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel#getStructure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Structure</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel#getStructure()
	 * @see #getVariableInscriptionLabel()
	 * @generated
	 */
	EReference getVariableInscriptionLabel_Structure();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token
	 * @generated
	 */
	EClass getToken();

	/**
	 * Returns the meta object for the containment reference list '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entity</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token#getEntity()
	 * @see #getToken()
	 * @generated
	 */
	EReference getToken_Entity();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType <em>Entity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Type</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType
	 * @generated
	 */
	EClass getEntityType();

	/**
	 * Returns the meta object for the attribute '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType#getText()
	 * @see #getEntityType()
	 * @generated
	 */
	EAttribute getEntityType_Text();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable#getText()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Text();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence <em>Variable Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Sequence</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence
	 * @generated
	 */
	EClass getVariableSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence#getVariable()
	 * @see #getVariableSequence()
	 * @generated
	 */
	EReference getVariableSequence_Variable();

	/**
	 * Returns the meta object for the attribute '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence#getMultiplicity()
	 * @see #getVariableSequence()
	 * @generated
	 */
	EAttribute getVariableSequence_Multiplicity();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity#getText()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Text();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking <em>PNID Marking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PNID Marking</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking
	 * @generated
	 */
	EClass getPNIDMarking();

	/**
	 * Returns the meta object for the containment reference '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking#getStructure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Structure</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking#getStructure()
	 * @see #getPNIDMarking()
	 * @generated
	 */
	EReference getPNIDMarking_Structure();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag <em>Token Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token Bag</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag
	 * @generated
	 */
	EClass getTokenBag();

	/**
	 * Returns the meta object for the containment reference list '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Token</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag#getToken()
	 * @see #getTokenBag()
	 * @generated
	 */
	EReference getTokenBag_Token();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PnidsFactory getPnidsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDImpl <em>PNID</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPNID()
		 * @generated
		 */
		EClass PNID = eINSTANCE.getPNID();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl <em>Place</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPlace()
		 * @generated
		 */
		EClass PLACE = eINSTANCE.getPlace();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__TYPE = eINSTANCE.getPlace_Type();

		/**
		 * The meta object literal for the '<em><b>Initial Marking</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__INITIAL_MARKING = eINSTANCE.getPlace_InitialMarking();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeLabelImpl <em>Entity Type Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeLabelImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityTypeLabel()
		 * @generated
		 */
		EClass ENTITY_TYPE_LABEL = eINSTANCE.getEntityTypeLabel();

		/**
		 * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_TYPE_LABEL__STRUCTURE = eINSTANCE.getEntityTypeLabel_Structure();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeSequenceImpl <em>Entity Type Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeSequenceImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityTypeSequence()
		 * @generated
		 */
		EClass ENTITY_TYPE_SEQUENCE = eINSTANCE.getEntityTypeSequence();

		/**
		 * The meta object literal for the '<em><b>Entity Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_TYPE_SEQUENCE__ENTITY_TYPE = eINSTANCE.getEntityTypeSequence_EntityType();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.ArcImpl <em>Arc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.ArcImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getArc()
		 * @generated
		 */
		EClass ARC = eINSTANCE.getArc();

		/**
		 * The meta object literal for the '<em><b>Inscription</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARC__INSCRIPTION = eINSTANCE.getArc_Inscription();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableInscriptionLabelImpl <em>Variable Inscription Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableInscriptionLabelImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariableInscriptionLabel()
		 * @generated
		 */
		EClass VARIABLE_INSCRIPTION_LABEL = eINSTANCE.getVariableInscriptionLabel();

		/**
		 * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_INSCRIPTION_LABEL__STRUCTURE = eINSTANCE.getVariableInscriptionLabel_Structure();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenImpl <em>Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getToken()
		 * @generated
		 */
		EClass TOKEN = eINSTANCE.getToken();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN__ENTITY = eINSTANCE.getToken_Entity();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeImpl <em>Entity Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntityType()
		 * @generated
		 */
		EClass ENTITY_TYPE = eINSTANCE.getEntityType();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_TYPE__TEXT = eINSTANCE.getEntityType_Text();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__TEXT = eINSTANCE.getVariable_Text();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableSequenceImpl <em>Variable Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.VariableSequenceImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getVariableSequence()
		 * @generated
		 */
		EClass VARIABLE_SEQUENCE = eINSTANCE.getVariableSequence();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_SEQUENCE__VARIABLE = eINSTANCE.getVariableSequence_Variable();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_SEQUENCE__MULTIPLICITY = eINSTANCE.getVariableSequence_Multiplicity();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__TEXT = eINSTANCE.getEntity_Text();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDMarkingImpl <em>PNID Marking</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PNIDMarkingImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getPNIDMarking()
		 * @generated
		 */
		EClass PNID_MARKING = eINSTANCE.getPNIDMarking();

		/**
		 * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PNID_MARKING__STRUCTURE = eINSTANCE.getPNIDMarking_Structure();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenBagImpl <em>Token Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenBagImpl
		 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PnidsPackageImpl#getTokenBag()
		 * @generated
		 */
		EClass TOKEN_BAG = eINSTANCE.getTokenBag();

		/**
		 * The meta object literal for the '<em><b>Token</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN_BAG__TOKEN = eINSTANCE.getTokenBag_Token();

	}

} //PnidsPackage
