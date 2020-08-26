import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDepense } from 'app/shared/model/depense.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './depense.reducer';

export interface IDepenseDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DepenseDeleteDialog = (props: IDepenseDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/depense');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.depenseEntity.id);
  };

  const { depenseEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>Confirm delete operation</ModalHeader>
      <ModalBody id="eAvicultureApp.depense.delete.question">Are you sure you want to delete this Depense?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-depense" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ depense }: IRootState) => ({
  depenseEntity: depense.entity,
  updateSuccess: depense.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DepenseDeleteDialog);
