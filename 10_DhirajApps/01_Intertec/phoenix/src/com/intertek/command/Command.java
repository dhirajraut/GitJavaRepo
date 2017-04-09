package com.intertek.command;

import java.util.List;

/**
 * Provide a generic way to execute a certain task.
 *
 **/
public interface Command
{
  /**
   * A generic method to let the implemenation class to do a certain task.
   *
   * @param params - a list of Objects.
   *
   **/
  void execute(List params);
}
