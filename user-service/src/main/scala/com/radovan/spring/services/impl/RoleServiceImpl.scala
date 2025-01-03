package com.radovan.spring.services.impl

import com.radovan.spring.converter.TempConverter
import com.radovan.spring.dto.RoleDto
import com.radovan.spring.repositories.RoleRepository
import com.radovan.spring.services.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.jdk.CollectionConverters._

@Service
class RoleServiceImpl extends RoleService{

  private var roleRepository:RoleRepository = _
  private var tempConverter:TempConverter = _

  @Autowired
  private def initialize(roleRepository: RoleRepository,tempConverter:TempConverter):Unit = {
    this.roleRepository = roleRepository
    this.tempConverter = tempConverter
  }

  @Transactional(readOnly = true)
  override def listAllByUserId(userId: Integer): Array[RoleDto] = {
    val allRoles = roleRepository.findAllByUserId(userId).asScala
    allRoles.collect{
      case roleEntity => tempConverter.roleEntityToDto(roleEntity)
    }.toArray
  }
}
