import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepenseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DepenseDetail = (props: IDepenseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { depenseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Depense [<b>{depenseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeDepense">Code Depense</span>
          </dt>
          <dd>{depenseEntity.codeDepense}</dd>
          <dt>
            <span id="quantite">Quantite</span>
          </dt>
          <dd>{depenseEntity.quantite}</dd>
          <dt>
            <span id="dateDemande">Date Demande</span>
          </dt>
          <dd>
            <TextFormat value={depenseEntity.dateDemande} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="etatDepense">Etat Depense</span>
          </dt>
          <dd>{depenseEntity.etatDepense}</dd>
          <dt>Produit</dt>
          <dd>{depenseEntity.produit ? depenseEntity.produit.id : ''}</dd>
          <dt>Foursnisseur</dt>
          <dd>{depenseEntity.foursnisseur ? depenseEntity.foursnisseur.id : ''}</dd>
          <dt>Facture</dt>
          <dd>{depenseEntity.facture ? depenseEntity.facture.id : ''}</dd>
          <dt>Phase Production</dt>
          <dd>{depenseEntity.phaseProduction ? depenseEntity.phaseProduction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/depense" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/depense/${depenseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ depense }: IRootState) => ({
  depenseEntity: depense.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DepenseDetail);
