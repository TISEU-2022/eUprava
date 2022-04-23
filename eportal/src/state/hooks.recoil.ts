import { useMemo } from 'react';
import { RecoilValue, useRecoilValueLoadable } from 'recoil';

const useRecoilValueLoadableOrDefault = <T>(
  value: RecoilValue<T>,
  defaultValue: T,
): T => {
  const loadable = useRecoilValueLoadable(value);
  switch (loadable.state) {
    case 'hasError':
    case 'loading':
      return defaultValue;
    case 'hasValue':
      return loadable.contents;
  }
};

export { useRecoilValueLoadableOrDefault };
