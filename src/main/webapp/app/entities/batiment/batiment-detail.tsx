import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './batiment.reducer';
import { IBatiment } from 'app/shared/model/batiment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBatimentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const BatimentDetail = (props: IBatimentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { batimentEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Batiment [<b>{batimentEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="codeBatiment">Code Batiment</span>
                    </Label>
                    <input type="text" className="form-control" value={batimentEntity.codeBatiment} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="surface">Surface</span>
                    </Label>
                    <input type="text" className="form-control" value={batimentEntity.surface} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/batiment" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/batiment/${batimentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ batiment }: IRootState) => ({
  batimentEntity: batiment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BatimentDetail);
