import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './stock.reducer';
import { IStock } from 'app/shared/model/stock.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStockDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const StockDetail = (props: IStockDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { stockEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Stock [<b>{stockEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="codeStock">Code Stock</span>
                    </Label>
                    <input type="text" className="form-control" value={stockEntity.codeStock} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="surface">Surface</span>
                    </Label>
                    <input type="text" className="form-control" value={stockEntity.surface} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/stock" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/stock/${stockEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ stock }: IRootState) => ({
  stockEntity: stock.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StockDetail);
