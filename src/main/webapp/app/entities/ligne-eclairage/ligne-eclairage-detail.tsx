import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ligne-eclairage.reducer';
import { ILigneEclairage } from 'app/shared/model/ligne-eclairage.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILigneEclairageDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneEclairageDetail = (props: ILigneEclairageDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ligneEclairageEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          LigneEclairage [<b>{ligneEclairageEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeLigne">Code Ligne</span>
          </dt>
          <dd>{ligneEclairageEntity.codeLigne}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{ligneEclairageEntity.description}</dd>
          <dt>
            <span id="allume">Allume</span>
          </dt>
          <dd>{ligneEclairageEntity.allume ? 'true' : 'false'}</dd>
          <dt>
            <span id="luxMax">Lux Max</span>
          </dt>
          <dd>{ligneEclairageEntity.luxMax}</dd>
          <dt>
            <span id="luxMin">Lux Min</span>
          </dt>
          <dd>{ligneEclairageEntity.luxMin}</dd>
          <dt>
            <span id="luxChoisi">Lux Choisi</span>
          </dt>
          <dd>{ligneEclairageEntity.luxChoisi}</dd>
        </dl>
        <Button tag={Link} to="/ligne-eclairage" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ligne-eclairage/${ligneEclairageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ligneEclairage }: IRootState) => ({
  ligneEclairageEntity: ligneEclairage.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneEclairageDetail);
