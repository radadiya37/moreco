import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { getEntities as getFournisseurs } from 'app/entities/fournisseur/fournisseur.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { getEntities as getFactures } from 'app/entities/facture/facture.reducer';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { getEntities as getPhaseProductions } from 'app/entities/phase-production/phase-production.reducer';
import { getEntity, updateEntity, createEntity, reset } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDepenseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DepenseUpdate = (props: IDepenseUpdateProps) => {
  const [produitId, setProduitId] = useState('0');
  const [foursnisseurId, setFoursnisseurId] = useState('0');
  const [factureId, setFactureId] = useState('0');
  const [phaseProductionId, setPhaseProductionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { depenseEntity, produits, fournisseurs, factures, phaseProductions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/depense');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProduits();
    props.getFournisseurs();
    props.getFactures();
    props.getPhaseProductions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...depenseEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="eAvicultureApp.depense.home.createOrEditLabel">Create or edit a Depense</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : depenseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="depense-id">ID</Label>
                  <AvInput id="depense-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeDepenseLabel" for="depense-codeDepense">
                  Code Depense
                </Label>
                <AvField id="depense-codeDepense" type="text" name="codeDepense" />
              </AvGroup>
              <AvGroup>
                <Label id="quantiteLabel" for="depense-quantite">
                  Quantite
                </Label>
                <AvField id="depense-quantite" type="string" className="form-control" name="quantite" />
              </AvGroup>
              <AvGroup>
                <Label id="dateDemandeLabel" for="depense-dateDemande">
                  Date Demande
                </Label>
                <AvField id="depense-dateDemande" type="date" className="form-control" name="dateDemande" />
              </AvGroup>
              <AvGroup>
                <Label id="etatDepenseLabel" for="depense-etatDepense">
                  Etat Depense
                </Label>
                <AvField id="depense-etatDepense" type="text" name="etatDepense" />
              </AvGroup>
              <AvGroup>
                <Label for="depense-produit">Produit</Label>
                <AvInput id="depense-produit" type="select" className="form-control" name="produit.id">
                  <option value="" key="0" />
                  {produits
                    ? produits.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="depense-foursnisseur">Foursnisseur</Label>
                <AvInput id="depense-foursnisseur" type="select" className="form-control" name="foursnisseur.id">
                  <option value="" key="0" />
                  {fournisseurs
                    ? fournisseurs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="depense-facture">Facture</Label>
                <AvInput id="depense-facture" type="select" className="form-control" name="facture.id">
                  <option value="" key="0" />
                  {factures
                    ? factures.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="depense-phaseProduction">Phase Production</Label>
                <AvInput id="depense-phaseProduction" type="select" className="form-control" name="phaseProduction.id">
                  <option value="" key="0" />
                  {phaseProductions
                    ? phaseProductions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/depense" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  produits: storeState.produit.entities,
  fournisseurs: storeState.fournisseur.entities,
  factures: storeState.facture.entities,
  phaseProductions: storeState.phaseProduction.entities,
  depenseEntity: storeState.depense.entity,
  loading: storeState.depense.loading,
  updating: storeState.depense.updating,
  updateSuccess: storeState.depense.updateSuccess
});

const mapDispatchToProps = {
  getProduits,
  getFournisseurs,
  getFactures,
  getPhaseProductions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DepenseUpdate);
