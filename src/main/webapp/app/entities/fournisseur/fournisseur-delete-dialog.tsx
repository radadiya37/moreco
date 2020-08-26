import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './fournisseur.reducer';

export interface IFournisseurDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FournisseurDeleteDialog = (props: IFournisseurDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/fournisseur');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.fournisseurEntity.id);
  };

  const { fournisseurEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>Confirm delete operation</ModalHeader>
      <ModalBody id="eAvicultureApp.fournisseur.delete.question">Are you sure you want to delete this Fournisseur?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-fournisseur" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ fournisseur }: IRootState) => ({
  fournisseurEntity: fournisseur.entity,
  updateSuccess: fournisseur.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FournisseurDeleteDialog);
