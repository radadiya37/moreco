import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

export interface IEmployeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const EmployeDetail = (props: IEmployeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { employeEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Employe [<b>{employeEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="prenom">Prenom</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.prenom} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="nom">Nom</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.nom} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="email">Email</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.email} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="numeroTel">Numero Tel</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.numeroTel} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="dateEmbauche">Date Embauche</span>
                    </Label>
                    <AvField id="employe-dateEmbauche" type="date" className="form-control" name="dateEmbauche" value={employeEntity.dateEmbauche} disabled/>
                    {/* <TextFormat value={employeEntity.dateEmbauche} type="date" format={APP_LOCAL_DATE_FORMAT} /> */}
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="salaire">Salaire</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.salaire} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="adresse">Adresse</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.adresse} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="cin">Cin</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.cin} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="dateNaissance">Date Naissance</span>
                    </Label>
                    <AvField id="employe-dateEmbauche" type="date" className="form-control" name="dateNaissance" value={employeEntity.dateNaissance} disabled/>
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="sexe">Sexe</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.sexe} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="fonction">Fonction</span>
                    </Label>
                    <input type="text" className="form-control" value={employeEntity.fonction ? employeEntity.fonction.id : ''} readOnly />
                  </AvGroup>

                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/employe" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/employe/${employeEntity.id}/edit`} replace color="primary">
                    <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                  </Button>
                </div>
              </div>
            </div>
          </AvForm>

        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ employe }: IRootState) => ({
  employeEntity: employe.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmployeDetail);
