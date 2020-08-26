import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFonction } from 'app/shared/model/fonction.model';
import { getEntities as getFonctions } from 'app/entities/fonction/fonction.reducer';
import { getEntity, updateEntity, createEntity, reset } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmployeUpdate = (props: IEmployeUpdateProps) => {
  const [fonctionId, setFonctionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { employeEntity, fonctions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/employe');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getFonctions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...employeEntity,
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
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h4 className="card-title">Create or edit a Employe</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : employeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="employe-id">ID</Label>
                  <AvInput id="employe-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="prenomLabel" for="employe-prenom">
                  Prenom
                </Label>
                <AvField id="employe-prenom" type="text" name="prenom" />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="employe-nom">
                  Nom
                </Label>
                <AvField id="employe-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="employe-email">
                  Email
                </Label>
                <AvField id="employe-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroTelLabel" for="employe-numeroTel">
                  Numero Tel
                </Label>
                <AvField id="employe-numeroTel" type="text" name="numeroTel" />
              </AvGroup>
              <AvGroup>
                <Label id="dateEmbaucheLabel" for="employe-dateEmbauche">
                  Date Embauche
                </Label>
                <AvField id="employe-dateEmbauche" type="date" className="form-control" name="dateEmbauche" />
              </AvGroup>
              <AvGroup>
                <Label id="salaireLabel" for="employe-salaire">
                  Salaire
                </Label>
                <AvField id="employe-salaire" type="string" className="form-control" name="salaire" />
              </AvGroup>
              <AvGroup>
                <Label id="adresseLabel" for="employe-adresse">
                  Adresse
                </Label>
                <AvField id="employe-adresse" type="text" name="adresse" />
              </AvGroup>
              <AvGroup>
                <Label id="cinLabel" for="employe-cin">
                  Cin
                </Label>
                <AvField id="employe-cin" type="text" name="cin" />
              </AvGroup>
              <AvGroup>
                <Label id="dateNaissanceLabel" for="employe-dateNaissance">
                  Date Naissance
                </Label>
                <AvField id="employe-dateNaissance" type="date" className="form-control" name="dateNaissance" />
              </AvGroup>
              <AvGroup>
                <Label id="sexeLabel" for="employe-sexe">
                  Sexe
                </Label>
                <AvField id="employe-sexe" type="text" name="sexe" />
              </AvGroup>
              <AvGroup>
                <Label for="employe-fonction">Fonction</Label>
                <AvInput id="employe-fonction" type="select" className="form-control" name="fonction.id">
                  <option value="" key="0" />
                  {fonctions
                    ? fonctions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/employe" replace color="info" className="btn btn-light">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating} className="btn btn-primary mr-2">
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  fonctions: storeState.fonction.entities,
  employeEntity: storeState.employe.entity,
  loading: storeState.employe.loading,
  updating: storeState.employe.updating,
  updateSuccess: storeState.employe.updateSuccess
});

const mapDispatchToProps = {
  getFonctions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmployeUpdate);
