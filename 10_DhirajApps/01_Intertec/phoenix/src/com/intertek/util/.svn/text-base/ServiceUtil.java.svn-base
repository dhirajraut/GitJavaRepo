package com.intertek.util;

import java.util.ArrayList;
import java.util.List;

import com.intertek.command.Command;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractVesselServiceExt;

public class ServiceUtil
{
  public static void applyCommand(AddJobOrder addJobOrder, Command command)
  {
    AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
    if(addJobContracts != null)
    {
      for(int i=0; i<addJobContracts.length; i++)
      {
        applyCommand(addJobContracts[i], command);
      }
    }
  }

  public static void applyCommand(AddJobContract addJobContract, Command command)
  {
    if((addJobContract == null) || (command == null)) return;

    List params = new ArrayList();
    params.add(addJobContract);
    params.add(addJobContract);
    command.execute(params);

    AddJobContractServices addJobContractServices = addJobContract.getAddJobContractServices();
    if(addJobContractServices != null)
    {
      List addedServiceExtList = addJobContractServices.getAddedJobContractServices();
      for(int i=0; i<addedServiceExtList.size(); i++)
      {
        JobContractServiceExt serviceExt = (JobContractServiceExt)addedServiceExtList.get(i);
        //recheckServiceResults(serviceExt);
        params = new ArrayList();
        params.add(addJobContract);
        params.add(serviceExt);
        command.execute(params);
      }

      params = new ArrayList();
      params.add(addJobContract);
      params.add(addJobContractServices);
      command.execute(params);
    }

    AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
    if(addJobContractVessels != null)
    {
      for(int i=0; i<addJobContractVessels.length; i++)
      {
        AddJobContractVesselServices addJobContractVesselServices = addJobContractVessels[i].getAddJobContractVesselServices();
        if(addJobContractVesselServices != null)
        {
          List addedServiceExtList = addJobContractVesselServices.getAddedJobContractVesselServices();
          for(int j=0; j<addedServiceExtList.size(); j++)
          {
            JobContractVesselServiceExt serviceExt = (JobContractVesselServiceExt)addedServiceExtList.get(j);
            //recheckServiceResults(serviceExt);
            params = new ArrayList();
            params.add(addJobContract);
            params.add(serviceExt);
            command.execute(params);
          }

          params = new ArrayList();
          params.add(addJobContract);
          params.add(addJobContractVesselServices);
          command.execute(params);
        }

        AddJobContractProd[] addJobContractProds = addJobContractVessels[i].getAddJobContractProds();
        if(addJobContractProds != null)
        {
          for(int k=0; k<addJobContractProds.length; k++)
          {
            AddJobContractProductServices addJobContractProductServices = addJobContractProds[k].getAddJobContractProductServices();
            if(addJobContractProductServices != null)
            {
              List addedServiceExtList = addJobContractProductServices.getAddedJobContractProductServices();
              for(int l=0; l<addedServiceExtList.size(); l++)
              {
                JobContractProductServiceExt serviceExt = (JobContractProductServiceExt)addedServiceExtList.get(l);
                //recheckServiceResults(serviceExt);
                params = new ArrayList();
                params.add(addJobContract);
                params.add(serviceExt);
                command.execute(params);
              }

              params = new ArrayList();
              params.add(addJobContract);
              params.add(addJobContractProductServices);
              command.execute(params);
            }
            params = new ArrayList();
            params.add(addJobContract);
            params.add(addJobContractProds[k]);
            command.execute(params);
          }
        }

        params = new ArrayList();
        params.add(addJobContract);
        params.add(addJobContractVessels[i]);
        command.execute(params);
      }
    }
  }

  public static void applyCommand(
    AddJobContractVessel addJobContractVessel,
    AddJobContract addJobContract,
    Command command
  )
  {
    if((addJobContract == null) || (command == null)) return;

    List params = new ArrayList();
    params.add(addJobContract);
    params.add(addJobContractVessel);
    command.execute(params);
  }
}
