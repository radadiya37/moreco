import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './emplacement.reducer';
import { IEmplacement } from 'app/shared/model/emplacement.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmplacementDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const EmplacementDetail = (props: IEmplacementDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { emplacementEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Emplacement [<b>{emplacementEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="codeEmplacement">Code Emplacement</span>
                    </Label>
                    <input type="text" className="form-control" value={emplacementEntity.codeEmplacement} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="volume">Volume</span>
                    </Label>
                    <input type="text" className="form-control" value={emplacementEntity.volume} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="addressLabel" for="address">
                      <span id="reserve">Reserve</span>
                    </Label>
                    <input type="text" className="form-control" value={emplacementEntity.reserve ? 'true' : 'false'} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="cityLabel" for="city">
                      <dt>Stock</dt>
                    </Label>
                    <input type="text" className="form-control" value={emplacementEntity.stock ? emplacementEntity.stock.id : ''} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/emplacement" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/emplacement/${emplacementEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ emplacement }: IRootState) => ({
  emplacementEntity: emplacement.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmplacementDetail);
