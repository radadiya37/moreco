import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './phase-production.reducer';

export interface IPhaseProductionDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PhaseProductionDeleteDialog = (props: IPhaseProductionDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/phase-production');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.phaseProductionEntity.id);
  };

  const { phaseProductionEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>Confirm delete operation</ModalHeader>
      <ModalBody id="eAvicultureApp.phaseProduction.delete.question">Are you sure you want to delete this PhaseProduction?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-phaseProduction" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ phaseProduction }: IRootState) => ({
  phaseProductionEntity: phaseProduction.entity,
  updateSuccess: phaseProduction.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PhaseProductionDeleteDialog);
