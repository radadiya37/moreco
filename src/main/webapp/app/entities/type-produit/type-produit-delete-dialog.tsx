import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ITypeProduit } from 'app/shared/model/type-produit.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './type-produit.reducer';

export interface ITypeProduitDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypeProduitDeleteDialog = (props: ITypeProduitDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/type-produit');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.typeProduitEntity.id);
  };

  const { typeProduitEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>Confirm delete operation</ModalHeader>
      <ModalBody id="eAvicultureApp.typeProduit.delete.question">Are you sure you want to delete this TypeProduit?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-typeProduit" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ typeProduit }: IRootState) => ({
  typeProduitEntity: typeProduit.entity,
  updateSuccess: typeProduit.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypeProduitDeleteDialog);
