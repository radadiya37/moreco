import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './log-parametre-environement.reducer';
import { ILogParametreEnvironement } from 'app/shared/model/log-parametre-environement.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILogParametreEnvironementDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LogParametreEnvironementDetail = (props: ILogParametreEnvironementDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { logParametreEnvironementEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          LogParametreEnvironement [<b>{logParametreEnvironementEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="temperature">Temperature</span>
          </dt>
          <dd>{logParametreEnvironementEntity.temperature}</dd>
          <dt>
            <span id="humidite">Humidite</span>
          </dt>
          <dd>{logParametreEnvironementEntity.humidite}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            <TextFormat value={logParametreEnvironementEntity.dateLog} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>Phase Production</dt>
          <dd>{logParametreEnvironementEntity.phaseProduction ? logParametreEnvironementEntity.phaseProduction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/log-parametre-environement" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/log-parametre-environement/${logParametreEnvironementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ logParametreEnvironement }: IRootState) => ({
  logParametreEnvironementEntity: logParametreEnvironement.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogParametreEnvironementDetail);
