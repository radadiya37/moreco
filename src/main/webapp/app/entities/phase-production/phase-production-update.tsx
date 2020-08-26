import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button, Row, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBatiment } from 'app/shared/model/batiment.model';
import { getEntities as getBatiments } from 'app/entities/batiment/batiment.reducer';
import { getEntity, updateEntity, createEntity, reset } from './phase-production.reducer';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
// import { ModalBody, ModalFooter } from 'react-bootstrap';

export interface IPhaseProductionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const PhaseProductionUpdate = (props: IPhaseProductionUpdateProps) => {
  const [batimentId, setBatimentId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);
  const [showModal, setShowModal] = useState(false);
  const [showDephanceModal, setShowDephanceModal] = useState(false);

  const { phaseProductionEntity, batiments, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/phase-production');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBatiments();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const toggleAddLogPModel = () => setShowModal(!showModal);

  const toggleAddDepenceModel = () => setShowDephanceModal(!showDephanceModal);

  // Dephance
  const cancelModel = () => {
    setShowModal(!showModal);
  };

  const cancelDephanceModel = () => {
    setShowDephanceModal(!showDephanceModal);
  };
  // cancelUpdate = () => this.setState({ showModal: !this.state.showModal, updateId: null });
  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...phaseProductionEntity,
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
    <div className="row">
      <div className="col-md-12">
        {loading ? (
          <p>Loading...</p>
        ) : (
            <div>
              <AvForm model={isNew ? {} : phaseProductionEntity} onSubmit={saveEntity}>
                <div className="row">
                  <div className="col-12 grid-margin stretch-card">
                    <div className="card">
                      <div className="card-body">
                        <h4 className="card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Phase Production</h4>
                        {!isNew ? (
                          <AvGroup>
                            <Label for="phase-production-id">ID</Label>
                            <AvInput id="phase-production-id" type="text" className="form-control" name="id" required readOnly />
                          </AvGroup>
                        ) : null}
                        <AvGroup>
                          <Label id="codePhaseLabel" for="phase-production-codePhase">
                            Code Phase
                </Label>
                          <AvField id="phase-production-codePhase" type="text" name="codePhase" />
                        </AvGroup>
                        <AvGroup>
                          <Label id="dateDebutLabel" for="phase-production-dateDebut">
                            Date Debut
                </Label>
                          <AvField id="phase-production-dateDebut" type="date" className="form-control" name="dateDebut" />
                        </AvGroup>
                        <AvGroup>
                          <Label id="dateFinLabel" for="phase-production-dateFin">
                            Date Fin
                </Label>
                          <AvField id="phase-production-dateFin" type="date" className="form-control" name="dateFin" />
                        </AvGroup>
                        <AvGroup>
                          <Label id="nombrePouletsLabel" for="phase-production-nombrePoulets">
                            Nombre Poulets
                </Label>
                          <AvField id="phase-production-nombrePoulets" type="string" className="form-control" name="nombrePoulets" />
                        </AvGroup>
                        <AvGroup>
                          <Label id="nombreDecesLabel" for="phase-production-nombreDeces">
                            Nombre Deces
                </Label>
                          <AvField id="phase-production-nombreDeces" type="string" className="form-control" name="nombreDeces" />
                        </AvGroup>
                        <AvGroup>
                          <Label for="phase-production-batiment">Batiment</Label>
                          <AvInput id="phase-production-batiment" type="select" className="form-control" name="batiment.id">
                            <option value="" key="0" />
                            {batiments
                              ? batiments.map(otherEntity => (
                                <option value={otherEntity.id} key={otherEntity.id}>
                                  {otherEntity.id}
                                </option>
                              ))
                              : null}
                          </AvInput>
                        </AvGroup>
                        {/* <Button tag={Link} id="cancel-save" to="/phase-production" className="btn btn-light">
                    <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
                  </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating} className="btn btn-primary mr-2">
                    <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button> */}
                      </div>
                    </div>
                  </div>
                </div>
                <div className="row">
                  <div className="col-12 grid-margin stretch-card">
                    <div className="card">
                      <div className="card-body">
                        <div className="row">
                          <h4 className="col-md-6 card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Depense</h4>
                          <div className="col-md-6" >
                            <Button style={{ float: 'right' }} className="purchased-items-done" id="station-add-fare" onClick={() => toggleAddDepenceModel()}>
                              <FontAwesomeIcon icon="save" />
                            &nbsp;
                            Add Depense
                            </Button>
                          </div>
                        </div>
                        <div className="table-responsive">
                          {/* {phaseProductionList && phaseProductionList.length > 0 ? ( */}
                          <table className="table table-striped">
                            <thead>
                              <tr>
                                <th>Code Depense</th>
                                <th>Quantite</th>
                                <th>Date Demande</th>
                                <th>Etat Depense</th>
                              </tr>
                            </thead>
                            <tbody>
                              {/* { (=> ( */}
                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                              </tr>
                              {/* ))} */}
                            </tbody>
                          </table>
                          {/* ) : ( */}
                !loading && <div className="alert alert-warning">No Depense found</div>
                          {/* )} */}
                        </div>
                        {/* <Button tag={Link} id="cancel-save" to="/phase-production" className="btn btn-light">
                    <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
                  </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating} className="btn btn-primary mr-2">
                    <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button> */}
                      </div>
                    </div>
                  </div>
                </div>
                <div className="row">
                  <div className="col-12 grid-margin stretch-card">
                    <div className="card">
                      <div className="card-body">
                        <div className="row">
                          <h4 className="col-md-6 card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">LogParametreEnvironement</h4>
                          <div className="col-md-6" >
                            <Button style={{ float: 'right' }} className="purchased-items-done" id="station-add-fare" onClick={() => toggleAddLogPModel()}>
                              <FontAwesomeIcon icon="save" />
                            &nbsp;
                            Add Log Parametre Environement
                            </Button>
                          </div>
                        </div>
                        <div className="table-responsive">
                          {/* {phaseProductionList && phaseProductionList.length > 0 ? ( */}
                          <table className="table table-striped">
                            <thead>
                              <tr>
                                <th>Temperature</th>
                                <th>Humidite</th>
                                <th>Date Log</th>
                              </tr>
                            </thead>
                            <tbody>
                              {/* { (=> ( */}
                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                              </tr>
                              {/* ))} */}
                            </tbody>
                          </table>
                          {/* ) : ( */}
                          {/* !loading && <div className="alert alert-warning">No Log Parametre Environement found</div> */}
                          {/* )} */}
                        </div>

                      </div>
                    </div>
                  </div>
                </div>
                <div className="row">
                  <div className="col-12 grid-margin stretch-card">
                    <div className="card">
                      <div className="card-body">
                        <Button tag={Link} id="cancel-save" to="/phase-production" className="btn btn-light">
                          <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
                        </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating} className="btn btn-primary mr-2">
                          <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </AvForm>
              <Modal isOpen={showModal}>
                <ModalHeader toggle={cancelModel} >Log Parametre Environement</ModalHeader>
                <ModalBody id="LogParametreEnvironement">
                  {/* <div className="row"> */}
                  <AvForm>
                    <AvGroup>
                      <Label id="temperatureLabel" for="log-parametre-environement-temperature">
                        Temperature
                </Label>
                      <AvField id="log-parametre-environement-temperature" type="string" className="form-control" name="temperature" />
                    </AvGroup>
                    <AvGroup>
                      <Label id="humiditeLabel" for="log-parametre-environement-humidite">
                        Humidite
                </Label>
                      <AvField id="log-parametre-environement-humidite" type="string" className="form-control" name="humidite" />
                    </AvGroup>
                    <AvGroup>
                      <Label id="dateLogLabel" for="log-parametre-environement-dateLog">
                        Date Log
                </Label>
                      <AvField id="log-parametre-environement-dateLog" type="date" className="form-control" name="dateLog" />
                    </AvGroup>
                  </AvForm>
                </ModalBody>
                <ModalFooter>
                  <Button color="secondary" onClick={cancelModel}>
                    <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
                  <Button id="jhi-confirm-delete-phaseProduction" color="danger">
                    <FontAwesomeIcon icon="trash" />
          &nbsp; Add
        </Button>
                </ModalFooter>
              </Modal>
{/* ********************************************* */}
              <Modal isOpen={showDephanceModal}>
                <ModalHeader toggle={cancelDephanceModel} >Dephance</ModalHeader>
                <ModalBody id="Dephance">
                  {/* <div className="row"> */}
                  <AvForm>
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
                  </AvForm>
                </ModalBody>
                <ModalFooter>
                  <Button color="secondary" onClick={cancelDephanceModel}>
                    <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
                  <Button id="jhi-confirm-delete-phaseProduction" color="danger">
                    <FontAwesomeIcon icon="trash" />
          &nbsp; Add
        </Button>
                </ModalFooter>
              </Modal>
            </div>
          )}
      </div>
    </div >
    // </div>
    // </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  batiments: storeState.batiment.entities,
  phaseProductionEntity: storeState.phaseProduction.entity,
  loading: storeState.phaseProduction.loading,
  updating: storeState.phaseProduction.updating,
  updateSuccess: storeState.phaseProduction.updateSuccess
});

const mapDispatchToProps = {
  getBatiments,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PhaseProductionUpdate);
